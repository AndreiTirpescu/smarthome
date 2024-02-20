package com.mysmarthome.devicecatalog.application;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"com.mysmarthome.devicecatalog.*"})
@EnableJpaRepositories(basePackages = {"com.mysmarthome.devicecatalog.*"})
@EntityScan(basePackages = {"com.mysmarthome.devicecatalog.*"})
@PropertySource({"classpath:dev-catalog-application.properties", "classpath:dev-catalog-application-local.properties"})
public class DeviceCatalogConfiguration {
}
