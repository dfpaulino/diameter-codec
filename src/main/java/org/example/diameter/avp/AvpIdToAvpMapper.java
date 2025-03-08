package org.example.diameter.avp;

import org.example.diameter.avp.common.*;
import org.example.diameter.avp.gx.*;
import org.example.diameter.avp.rx.AccessNetworkChargingAddress;

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
    private static AvpCreator<DestinationRealm> destinationRealmAvpCreator = ((header, buf, pos) -> new DestinationRealm(header, buf, pos));
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

    private static AvpCreator<QoSNegotiation> qoSNegotiationAvpCreator = ((header, buf, pos) -> new QoSNegotiation(header, buf, pos));

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

    private static AvpCreator<BearerControlMode> bearerControlModeAvpCreator = ((header, buf, pos) -> new BearerControlMode(header, buf, pos));
    private static AvpCreator<BearerUsage> bearerUsageAvpCreator = ((header, buf, pos) -> new BearerUsage(header, buf, pos));
    private static AvpCreator<BearerOperation> bearerOperationAvpCreator = ((header, buf, pos) -> new BearerOperation(header, buf, pos));

    private static AvpCreator<SupportedFeatures> supportedFeaturesAvpCreator = ((header, buf, pos) -> new SupportedFeatures(header, buf, pos));
    private static AvpCreator<FeatureListId> featureListIdAvpCreator = ((header, buf, pos) -> new FeatureListId(header, buf, pos));
    private static AvpCreator<FeatureList> featureListAvpCreator = ((header, buf, pos) -> new FeatureList(header, buf, pos));

    private static AvpCreator<ServiceIdentifier> serviceIdentifierAvpCreator = ((header, buf, pos) -> new ServiceIdentifier(header, buf, pos));
    private static AvpCreator<RatingGroup> ratingGroupAvpCreator = ((header, buf, pos) -> new RatingGroup(header, buf, pos));
    private static AvpCreator<ResultCode> resultCodeAvpCreator = ((header, buf, pos) -> new ResultCode(header, buf, pos));
    private static AvpCreator<TerminationCause> terminationCauseAvpCreator = ((header, buf, pos) -> new TerminationCause(header, buf, pos));

    private static AvpCreator<CcRequestNumber> ccRequestNumberAvpCreator = ((header, buf, pos) -> new CcRequestNumber(header, buf, pos));
    private static AvpCreator<CcRequestType> ccRequestTypeAvpCreator = ((header, buf, pos) -> new CcRequestType(header, buf, pos));
    private static AvpCreator<CalledStationId> calledStationIdAvpCreator = ((header, buf, pos) -> new CalledStationId(header, buf, pos));
    private static AvpCreator<SessionId> sessionIdAvpCreator = ((header, buf, pos) -> new SessionId(header, buf, pos));

    private static AvpCreator<GppSgsnAddress> gppSgsnAddressAvpCreator = ((header, buf, pos) -> new GppSgsnAddress(header, buf, pos));
    private static AvpCreator<GppUserLocationInfo> gppUserLocationInfoAvpCreator = ((header, buf, pos) -> new GppUserLocationInfo(header, buf, pos));

    private static AvpCreator<FrameIpAddress> frameIpAddressAvpCreator = ((header, buf, pos) -> new FrameIpAddress(header, buf, pos));
    private static AvpCreator<IpCanType> ipCanTypeAvpCreator = ((header, buf, pos) -> new IpCanType(header, buf, pos));
    private static AvpCreator<RatType> ratTypeAvpCreator = ((header, buf, pos) -> new RatType(header, buf, pos));
    private static AvpCreator<GppRatType> gppRatTypeAvpCreator = ((header, buf, pos) -> new GppRatType(header, buf, pos));
    private static AvpCreator<AccessNetworkChargingAddress> accessNetworkChargingAddressAvpCreator = ((header, buf, pos) -> new AccessNetworkChargingAddress(header, buf, pos));
    private static AvpCreator<AccessNetworkChargingIdentifierGx> accessNetworkChargingIdentifierGxAvpCreator = ((header, buf, pos) -> new AccessNetworkChargingIdentifierGx(header, buf, pos));
    private static AvpCreator<AccessNetworkChargingIdentifierValue> accessNetworkChargingIdentifierValueAvpCreator = ((header, buf, pos) -> new AccessNetworkChargingIdentifierValue(header, buf, pos));

    private static AvpCreator<NetworkRequestSupport> networkRequestSupportAvpCreator = ((header, buf, pos) -> new NetworkRequestSupport(header, buf, pos));
    private static AvpCreator<QoSUpgrade> qoSUpgradeAvpCreator = ((header, buf, pos) -> new QoSUpgrade(header, buf, pos));


    synchronized private static void loadMap() {
        avpMapper.put(HostIpAddress.avpCode, new AvpDefinition(hostIpAddressAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(AuthApplicationId.avpCode, new AvpDefinition(authApplicationIdAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(OriginHost.avpCode, new AvpDefinition(originHostAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(DestinationRealm.avpCode, new AvpDefinition(destinationRealmAvpCreator, AvpType.NOT_GROUP));
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
        avpMapper.put(QoSNegotiation.avpCode, new AvpDefinition(qoSNegotiationAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(QoSUpgrade.avpCode, new AvpDefinition(qoSUpgradeAvpCreator, AvpType.NOT_GROUP));

        avpMapper.put(MaxRequestedBandwidthUL.avpCode, new AvpDefinition(maxRequestedBandwidthULAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(MaxRequestedBandwidthDL.avpCode, new AvpDefinition(maxRequestedBandwidthDLAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(GuaranteedBitrateUL.avpCode, new AvpDefinition(guaranteedBitrateULAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(GuaranteedBitrateDL.avpCode, new AvpDefinition(guaranteedBitrateDLAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(BearerIdentifier.avpCode, new AvpDefinition(bearerIdentifierAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(AllocationRetentionPriority.avpCode, new AvpDefinition(allocationRetentionPriorityAvpCreator, AvpType.GROUPED));
        avpMapper.put(UserEquipmentInfoValue.avpCode, new AvpDefinition(userEquipmentInfoValueAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(ApnAggregateMaxBitrateUL.avpCode, new AvpDefinition(apnAggregateMaxBitrateULAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(ApnAggregateMaxBitrateDL.avpCode, new AvpDefinition(apnAggregateMaxBitrateDLAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(PriorityLevel.avpCode, new AvpDefinition(priorityLevelAvpCreator, AvpType.NOT_GROUP));

        avpMapper.put(BearerControlMode.avpCode, new AvpDefinition(bearerControlModeAvpCreator, AvpType.GROUPED));
        avpMapper.put(BearerOperation.avpCode, new AvpDefinition(bearerOperationAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(BearerUsage.avpCode, new AvpDefinition(bearerUsageAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(SupportedFeatures.avpCode, new AvpDefinition(supportedFeaturesAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(FeatureList.avpCode, new AvpDefinition(featureListAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(FeatureListId.avpCode, new AvpDefinition(featureListIdAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(ServiceIdentifier.avpCode, new AvpDefinition(serviceIdentifierAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(RatingGroup.avpCode, new AvpDefinition(ratingGroupAvpCreator, AvpType.GROUPED));
        avpMapper.put(ResultCode.avpCode, new AvpDefinition(resultCodeAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(TerminationCause.avpCode, new AvpDefinition(terminationCauseAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(CcRequestType.avpCode, new AvpDefinition(ccRequestTypeAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(CcRequestNumber.avpCode, new AvpDefinition(ccRequestNumberAvpCreator, AvpType.NOT_GROUP));

        avpMapper.put(NetworkRequestSupport.avpCode, new AvpDefinition(networkRequestSupportAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(CalledStationId.avpCode, new AvpDefinition(calledStationIdAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(SessionId.avpCode, new AvpDefinition(sessionIdAvpCreator, AvpType.GROUPED));
        avpMapper.put(GppSgsnAddress.avpCode, new AvpDefinition(gppSgsnAddressAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(GppUserLocationInfo.avpCode, new AvpDefinition(gppUserLocationInfoAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(FrameIpAddress.avpCode, new AvpDefinition(frameIpAddressAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(IpCanType.avpCode, new AvpDefinition(ipCanTypeAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(RatType.avpCode, new AvpDefinition(ratTypeAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(GppRatType.avpCode, new AvpDefinition(gppRatTypeAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(AccessNetworkChargingAddress.avpCode, new AvpDefinition(accessNetworkChargingAddressAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(AccessNetworkChargingIdentifierGx.avpCode, new AvpDefinition(accessNetworkChargingIdentifierGxAvpCreator, AvpType.NOT_GROUP));
        avpMapper.put(AccessNetworkChargingIdentifierValue.avpCode, new AvpDefinition(accessNetworkChargingIdentifierValueAvpCreator, AvpType.NOT_GROUP));
    }

}
