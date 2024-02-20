package com.mysmarthome.devicecatalog.infrastructure.events;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "devicecatalog.domain.events")
@Data
public class DeviceCatalogDomainEventsConfiguration {
    public String topic;

    public String routingKey;
}
