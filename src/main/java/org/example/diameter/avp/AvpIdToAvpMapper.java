package org.example.diameter.avp;

import org.example.diameter.avp.common.*;
import org.example.diameter.avp.gx.*;

import java.util.HashMap;
import java.util.Map;


public class AvpIdToAvpMapper {
    private static Map<Integer, AvpDefinition> avpMapper = new HashMap<>();

    public static Map<Integer, AvpDefinition> getAvpMapper() {
        if (avpMapper.isEmpty()) {
            loadMap();
        }
        return avpMapper;
    }

    public static class AvpDefinition {
        public AvpCreator<? extends Avp<?>> avpCreator;
        public AvpType type;

        public AvpDefinition(AvpCreator<? extends Avp<?>> avpCreator, AvpType type) {
            this.avpCreator = avpCreator;
            this.type = type;
        }
    }

    public enum AvpType {
        NOT_GROUP, GROUPED
    }

    @FunctionalInterface
    public interface AvpCreator<R extends Avp<?>> {
        R createInstance(AvpHeader header, byte[] buf, int pos);
    }

    private static AvpCreator<OriginHost> originHostAvpCreator = ((header, buf, pos) -> new OriginHost(header, buf, pos));
    private static AvpCreator<OriginRealm> originRealmAvpCreator = ((header, buf, pos) -> new OriginRealm(header, buf, pos));
    private static AvpCreator<OriginStateId> originStateIdAvpCreator = ((header, buf, pos) -> new OriginStateId(header, buf, pos));
    private static AvpCreator<ProductName> productNameAvpCreator = ((header, buf, pos) -> new ProductName(header, buf, pos));
    private static AvpCreator<SupportedVendorId> supportedVendorIdAvpCreator = ((header, buf, pos) -> new SupportedVendorId(header, buf, pos));
    private static AvpCreator<VendorId> vendorIdAvpCreator = ((header, buf, pos) -> new VendorId(header, buf, pos));
    private static AvpCreator<VendorSpecificApplicationId> vendorSpecificApplicationIdAvpCreator = ((header, buf, pos) -> new VendorSpecificApplicationId(header, buf, pos));
    private static AvpCreator<HostIpAddress> hostIpAddressAvpCreator = ((header, buf, pos) -> new HostIpAddress(header, buf, pos));
    private static AvpCreator<AuthApplicationId> authApplicationIdAvpCreator = ((header, buf, pos) -> new AuthApplicationId(header, buf, pos));
    private static AvpCreator<SubscriptionIdType> subscriptionIdTypeAvpCreator = ((header, buf, pos) -> new SubscriptionIdType(header, buf, pos));
    private static AvpCreator<SubscriptionIdData> subscriptionIdDataAvpCreator = ((header, buf, pos) -> new SubscriptionIdData(header, buf, pos));
    private static AvpCreator<SubscriptionId> subscriptionIdAvpCreator = ((header, buf, pos) -> new SubscriptionId(header, buf, pos));

    private static AvpCreator<UserEquipmentInfo> userEquipmentInfoAvpCreator = ((header, buf, pos) -> new UserEquipmentInfo(header, buf, pos));
    private static AvpCreator<UserEquipmentInfoType> userEquipmentInfoTypeAvpCreator = ((header, buf, pos) -> new UserEquipmentInfoType(header, buf, pos));
    private static AvpCreator<UserEquipmentInfoValue> userEquipmentInfoValueAvpCreator = ((header, buf, pos) -> new UserEquipmentInfoValue(header, buf, pos));

    private static AvpCreator<QoSInformation> qoSInformationAvpCreator = ((header, buf, pos) -> new QoSInformation(header, buf, pos));
    private static AvpCreator<QoSClassIdentifier> qoSClassIdentifierAvpCreator = ((header, buf, pos) -> new QoSClassIdentifier(header, buf, pos));
    private static AvpCreator<MaxRequestedBandwidthUL> maxRequestedBandwidthULAvpCreator = ((header, buf, pos) -> new MaxRequestedBandwidthUL(header, buf, pos));
    private static AvpCreator<MaxRequestedBandwidthDL> maxRequestedBandwidthDLAvpCreator = ((header, buf, pos) -> new MaxRequestedBandwidthDL(header, buf, pos));
    private static AvpCreator<GuaranteedBitrateUL> guaranteedBitrateULAvpCreator = ((header, buf, pos) -> new GuaranteedBitrateUL(header, buf, pos));
    private static AvpCreator<GuaranteedBitrateDL> guaranteedBitrateDLAvpCreator = ((header, buf, pos) -> new GuaranteedBitrateDL(header, buf, pos));
    private static AvpCreator<BearerIdentifier> bearerIdentifierAvpCreator = ((header, buf, pos) -> new BearerIdentifier(header, buf, pos));
    private static AvpCreator<ApnAggregateMaxBitrateUL> apnAggregateMaxBitrateULAvpCreator = ((header, buf, pos) -> new ApnAggregateMaxBitrateUL(header, buf, pos));
    private static AvpCreator<ApnAggregateMaxBitrateDL> apnAggregateMaxBitrateDLAvpCreator = ((header, buf, pos) -> new ApnAggregateMaxBitrateDL(header, buf, pos));
    private static AvpCreator<AllocationRetentionPriority> allocationRetentionPriorityAvpCreator = ((header, buf, pos) -> new AllocationRetentionPriority(header, buf, pos));
    private static AvpCreator<PriorityLevel> priorityLevelAvpCreator = ((header, buf, pos) -> new PriorityLevel(header, buf, pos));


    synchronized private static void loadMap() {
        avpMapper.put(HostIpAddress.avpCode, new AvpDefinition(hostIpAddressAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(AuthApplicationId.avpCode, new AvpDefinition(authApplicationIdAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(OriginHost.avpCode, new AvpDefinition(originHostAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(ProductName.avpCode, new AvpDefinition(productNameAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(OriginRealm.avpCode, new AvpDefinition(originRealmAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(OriginStateId.avpCode, new AvpDefinition(originStateIdAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(SupportedVendorId.avpCode, new AvpDefinition(supportedVendorIdAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(VendorId.avpCode, new AvpDefinition(vendorIdAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(VendorSpecificApplicationId.avpCode, new AvpDefinition(vendorSpecificApplicationIdAvpCreator, AvpType.GROUPED));

        avpMapper.put(SubscriptionIdType.avpCode, new AvpDefinition(subscriptionIdTypeAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(SubscriptionIdData.avpCode, new AvpDefinition(subscriptionIdDataAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(SubscriptionId.avpCode, new AvpDefinition(subscriptionIdAvpCreator, AvpType.GROUPED));

        avpMapper.put(UserEquipmentInfo.avpCode, new AvpDefinition(userEquipmentInfoAvpCreator, AvpType.GROUPED));
        avpMapper.put(UserEquipmentInfoType.avpCode, new AvpDefinition(userEquipmentInfoTypeAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(UserEquipmentInfoValue.avpCode, new AvpDefinition(userEquipmentInfoValueAvpCreator, AvpType.NOT_GROUP));


        avpMapper.put(QoSInformation.avpCode, new AvpDefinition(qoSInformationAvpCreator, AvpType.GROUPED));
        avpMapper.put(QoSClassIdentifier.avpCode, new AvpDefinition(qoSClassIdentifierAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(MaxRequestedBandwidthUL.avpCode, new AvpDefinition(maxRequestedBandwidthULAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(MaxRequestedBandwidthDL.avpCode, new AvpDefinition(maxRequestedBandwidthDLAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(GuaranteedBitrateUL.avpCode, new AvpDefinition(guaranteedBitrateULAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(GuaranteedBitrateDL.avpCode, new AvpDefinition(guaranteedBitrateDLAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(BearerIdentifier.avpCode, new AvpDefinition(bearerIdentifierAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(AllocationRetentionPriority.avpCode, new AvpDefinition(allocationRetentionPriorityAvpCreator, AvpType.GROUPED));
        avpMapper.put(UserEquipmentInfoValue.avpCode, new AvpDefinition(userEquipmentInfoValueAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(ApnAggregateMaxBitrateUL.avpCode, new AvpDefinition(apnAggregateMaxBitrateULAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(ApnAggregateMaxBitrateDL.avpCode, new AvpDefinition(apnAggregateMaxBitrateDLAvpCreator, AvpType.NOT_GROUP));

    }


}
