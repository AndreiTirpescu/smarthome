package com.mysmarthome.homesystemmanagement.infrastructure.events;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "userprofiling.domain.events")
@Data
public class UserProfilingDomainEventConfiguration {
    public String topic;

    public String routingKey;
}
