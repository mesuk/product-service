package com.skarim.product.config;

import com.netflix.appinfo.AmazonInfo;
import com.netflix.appinfo.DataCenterInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;

@ConfigurationProperties("eureka.instance")
public class EurekaInstanceConfig extends EurekaInstanceConfigBean {
    private static final Logger logger = LoggerFactory.getLogger(EurekaInstanceConfig.class);
    protected String namespace = "eureka.";
    private DataCenterInfo info;

    public EurekaInstanceConfig(){
        this.info = initDataCenterInfo();
    }

    public EurekaInstanceConfig(InetUtils inetUtils) {
        super(inetUtils);
    }

    private DataCenterInfo initDataCenterInfo() {
        DataCenterInfo info;
        try {
            info = AmazonInfo.Builder.newBuilder().autoBuild(namespace);
            logger.info("Datacenter is: " + DataCenterInfo.Name.Amazon);
        } catch (Throwable e) {
            logger.error("Cannot initialize amazon info :", e);
            throw new RuntimeException(e);
        }
        // Instance id being null means we could not get the amazon metadata
        AmazonInfo amazonInfo = (AmazonInfo) info;
        if (amazonInfo.get(AmazonInfo.MetaDataKey.instanceId) == null) {
            throw new RuntimeException(
                    "Your datacenter is defined as cloud but we are not able to get the amazon metadata to "
                            + "register. \nSet the property " + namespace + "validateInstanceId to false to ignore the"
                            + "metadata call");

        } else if ((amazonInfo.get(AmazonInfo.MetaDataKey.publicHostname) == null)
                && (amazonInfo.get(AmazonInfo.MetaDataKey.localIpv4) != null)) {
            // This might be a case of VPC where the instance id is not null, but
            // public hostname might be null
            amazonInfo.getMetadata().put(AmazonInfo.MetaDataKey.publicHostname.getName(),
                    (amazonInfo.get(AmazonInfo.MetaDataKey.localIpv4)));
        }
        return info;
    }

    /*
     * (non-Javadoc)
     * @see com.netflix.appinfo.AbstractInstanceConfig#getHostName()
     */
    @Override
    public String getHostName(boolean refresh) {
        return ((AmazonInfo) info).get(AmazonInfo.MetaDataKey.publicHostname);
    }

    @Override
    public String getIpAddress() {
        String publicIp = ((AmazonInfo) info).get(AmazonInfo.MetaDataKey.publicIpv4);
        return  publicIp== null ?
                ((AmazonInfo) info).get(AmazonInfo.MetaDataKey.localIpv4) : publicIp;
    }

    @Override
    public DataCenterInfo getDataCenterInfo() {
        return this.info;
    }
}