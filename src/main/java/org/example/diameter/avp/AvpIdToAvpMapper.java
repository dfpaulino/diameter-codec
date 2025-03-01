package org.example.diameter.avp;

import org.example.diameter.avp.common.*;

import java.util.HashMap;
import java.util.Map;


public class AvpIdToAvpMapper {
    private static Map<Integer,AvpDefinition> avpMapper=new HashMap<>();

    public static Map<Integer,AvpDefinition> getAvpMapper(){
        if(avpMapper.isEmpty()){
            loadMap();
        }
        return avpMapper;
    }
    public static class AvpDefinition {
        public AvpCreator<? extends Avp<?>> avpCreator;
        public AvpType type;
        public AvpDefinition(AvpCreator<? extends Avp<?>>  avpCreator, AvpType type) {
            this.avpCreator = avpCreator;
            this.type = type;
        }
    }
    public enum AvpType {
        NOT_GROUP,GROUPED
    }

    @FunctionalInterface
    public interface AvpCreator<R extends Avp<?>> {
        R createInstance(AvpHeader header, byte[] buf, int pos);
    }

    private static AvpCreator<OriginHost> originHostAvpCreator =((header, buf, pos) -> new OriginHost(header,buf,pos));
    private static AvpCreator<OriginRealm> originRealmAvpCreator=((header, buf, pos) -> new OriginRealm(header,buf,pos));
    private static AvpCreator<OriginStateId> originStateIdAvpCreator =((header, buf, pos) -> new OriginStateId(header,buf,pos));
    private static AvpCreator<ProductName> productNameAvpCreator=((header, buf, pos) -> new ProductName(header,buf,pos));
    private static AvpCreator<SupportedVendorId> supportedVendorIdAvpCreator =((header, buf, pos) -> new SupportedVendorId(header,buf,pos));
    private static AvpCreator<VendorId> vendorIdAvpCreator=((header, buf, pos) -> new VendorId(header,buf,pos));
    private static AvpCreator<VendorSpecificApplicationId> vendorSpecificApplicationIdAvpCreator =((header, buf, pos) -> new VendorSpecificApplicationId(header,buf,pos));
    private static AvpCreator<HostIpAddress> hostIpAddressAvpCreator=((header, buf, pos) -> new HostIpAddress(header,buf,pos));
    private static AvpCreator<AuthApplicationId> authApplicationIdAvpCreator=((header, buf, pos) -> new AuthApplicationId(header,buf,pos));
    private static AvpCreator<SubscriptionIdType> subscriptionIdTypeAvpCreator=((header, buf, pos) -> new SubscriptionIdType(header,buf,pos));
    private static AvpCreator<SubscriptionIdData> subscriptionIdDataAvpCreator=((header, buf, pos) -> new SubscriptionIdData(header,buf,pos));
    private static AvpCreator<SubscriptionId> subscriptionIdAvpCreator=((header, buf, pos) -> new SubscriptionId(header,buf,pos));


    synchronized private static void loadMap() {
        avpMapper.put(257, new AvpDefinition(hostIpAddressAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(258, new AvpDefinition(authApplicationIdAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(264, new AvpDefinition(originHostAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(269, new AvpDefinition(productNameAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(296, new AvpDefinition(originRealmAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(278, new AvpDefinition(originStateIdAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(265, new AvpDefinition(supportedVendorIdAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(266, new AvpDefinition(vendorIdAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(260, new AvpDefinition(vendorSpecificApplicationIdAvpCreator, AvpType.GROUPED));

        avpMapper.put(450, new AvpDefinition(subscriptionIdTypeAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(444, new AvpDefinition(subscriptionIdDataAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(443, new AvpDefinition(subscriptionIdAvpCreator, AvpType.GROUPED));

    }


}
