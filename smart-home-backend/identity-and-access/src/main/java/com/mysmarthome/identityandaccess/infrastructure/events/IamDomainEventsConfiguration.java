package com.mysmarthome.identityandaccess.infrastructure.events;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "iam.domain.events")
@Data
public class IamDomainEventsConfiguration {
    public String topic;

    public String routingKey;
}
