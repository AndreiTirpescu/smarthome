package com.mysmarthome.identityandaccess.infrastructure.token;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
@ConfigurationProperties(prefix = "iam.security.token")
@Data
public class TokenConfiguration {
    public String issuer;
    public Long durationInSeconds;
    public Long refreshDurationInSeconds;
    public UUID keyId;
    public String privateKey;
    public String publicKey;
}
