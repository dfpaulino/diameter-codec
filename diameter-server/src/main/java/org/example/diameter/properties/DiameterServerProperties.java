package org.example.diameter.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "diameter.server")
@Setter
@Getter
public class DiameterServerProperties {
    String hostIp;
    String originHost;
    String originRealm;
    String productName;

    int inBufferSize=100;


}
