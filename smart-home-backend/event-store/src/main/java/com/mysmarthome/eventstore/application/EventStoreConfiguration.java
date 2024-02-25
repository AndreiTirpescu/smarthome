package com.mysmarthome.eventstore.application;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"com.mysmarthome.eventstore.*"})
@EnableJpaRepositories(basePackages = {"com.mysmarthome.eventstore.*"})
@EntityScan(basePackages = {"com.mysmarthome.eventstore.*"})
@PropertySource({"classpath:eventstore-application.properties", "classpath:eventstore-application-local.properties"})

public class EventStoreConfiguration {
}
