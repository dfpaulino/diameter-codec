package org.example.diameter.handlers.common;

import org.example.diameter.DiameterReqContext;
import org.example.diameter.ModifiableDiameterReqContext;
import org.example.diameter.avp.common.*;
import org.example.diameter.avp.enums.ResultCodeEnum;
import org.example.diameter.avp.types.Address;
import org.example.diameter.exceptions.CommandCodeNotSupported;
import org.example.diameter.handlers.DiameterPacketHandler;
import org.example.diameter.packet.enums.DiameterApplicationId;
import org.example.diameter.packet.enums.DiameterCmdCode;
import org.example.diameter.packet.messages.CapabilitiesExchangeAnswer;
import org.example.diameter.packet.messages.CapabilitiesExchangeRequest;
import org.example.diameter.packet.messages.DeviceWatchDogAnswer;
import org.example.diameter.packet.utils.DiameterPacketGenerateBase;
import org.example.diameter.properties.DiameterServerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;

public class BasePacketHandler implements DiameterPacketHandler {

    private static final Logger logger = LoggerFactory.getLogger(BasePacketHandler.class);
    //support only GX...for the moment
    private static final Predicate<VendorSpecificApplicationId> IS_APP_SUPPORTED = (vsa -> {
        return vsa.getData().getAuthApplicationId().getData()==(DiameterApplicationId._3GPP_GX.getValue());
    });

    private final DiameterServerProperties properties;

    public BasePacketHandler(DiameterServerProperties properties) {
        this.properties = properties;
    }

    public Mono<DiameterReqContext> handle(DiameterReqContext reqContext) {
        switch (DiameterCmdCode.of(reqContext.getRequest().getHeader().getCommandCode())) {
            case CAPABILITIES_EXCHANGE -> {
                return handleCer(reqContext);
            }
            case DWD -> {
                return handleDwd(reqContext);
            }
            default -> {
                logger.error("Command code {} not supported for ApplicationId {}",
                        reqContext.getRequest().getHeader().getCommandCode(), reqContext.getRequest().getHeader().getApplicationId());
                return Mono.error(new CommandCodeNotSupported(("Command code {} not supported for ApplicationId {}"
                        .formatted(reqContext.getRequest().getHeader().getCommandCode(), reqContext.getRequest()
                                .getHeader().getApplicationId()))));
            }
        }

    }

    private Mono<DiameterReqContext> handleCer(DiameterReqContext context) {
        CapabilitiesExchangeRequest cer = (CapabilitiesExchangeRequest) context.getRequest();
        List<VendorSpecificApplicationId> supportedVendorSpecificApplicationIdList = cer.getVendorSpecificApplicationId().stream()
                .filter(IS_APP_SUPPORTED).toList();

        CapabilitiesExchangeAnswer cea = new CapabilitiesExchangeAnswer();
        cea.setHeader(DiameterPacketGenerateBase.generateHeaderFrom(context.getRequest()));
        cea.setOriginHost(new OriginHost(properties.getOriginHost()));
        cea.setOriginRealm(new OriginRealm(properties.getOriginRealm()));
        cea.setHostIpAddress(new HostIpAddress(new Address((short) 1, properties.getHostIp())));
        cea.setOriginStateId(new OriginStateId(((CapabilitiesExchangeRequest) context.getRequest()).getOriginStateId().getData()));

        if (!supportedVendorSpecificApplicationIdList.isEmpty()) {
            cea.setResultCode(new ResultCode(ResultCodeEnum.DIAMETER_SUCCESS.getValue()));
            cea.setVendorId(new VendorId(org.example.diameter.avp.enums.VendorId.CMN.getValue()));
            cea.setProductName(new ProductName(properties.getProductName()));
            //set supported vendor application Id..Gx only
            VendorSpecificApplicationId vendorSpecificApplicationId = new VendorSpecificApplicationId();
            vendorSpecificApplicationId.setVendorId(new VendorId(org.example.diameter.avp.enums.VendorId.GPP.getValue()));
            vendorSpecificApplicationId.setAuthApplicationId(new AuthApplicationId((int) DiameterApplicationId._3GPP_GX.getValue()));
            cea.setVendorSpecificApplicationId(vendorSpecificApplicationId);

        } else {
            cea.setResultCode(new ResultCode(ResultCodeEnum.DIAMETER_APPLICATION_UNSUPPORTED.getValue()));
        }
        return Mono.just(ModifiableDiameterReqContext.create().from(context)
                .setResponse(cea)
                .setRespTime(System.currentTimeMillis())
        );
    }

    private Mono<DiameterReqContext> handleDwd(DiameterReqContext context) {
        DeviceWatchDogAnswer dwa = new DeviceWatchDogAnswer();
        dwa.setHeader(DiameterPacketGenerateBase.generateHeaderFrom(context.getRequest()));
        dwa.setOriginHost(new OriginHost(properties.getOriginHost()));
        dwa.setOriginRealm(new OriginRealm(properties.getOriginRealm()));

        return Mono.just(ModifiableDiameterReqContext.create().from(context)
                .setResponse(dwa)
                .setRespTime(System.currentTimeMillis())
        );
    }
}
