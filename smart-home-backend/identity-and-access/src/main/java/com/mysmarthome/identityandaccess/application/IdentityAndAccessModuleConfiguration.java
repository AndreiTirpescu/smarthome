package com.mysmarthome.identityandaccess.application;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"com.mysmarthome.identityandaccess.*"})
@EnableJpaRepositories(basePackages = {"com.mysmarthome.identityandaccess.*"})
@EntityScan(basePackages = {"com.mysmarthome.identityandaccess.*"})
@PropertySource({"classpath:iam-application.properties", "classpath:iam-application-local.properties"})
public class IdentityAndAccessModuleConfiguration {

}
