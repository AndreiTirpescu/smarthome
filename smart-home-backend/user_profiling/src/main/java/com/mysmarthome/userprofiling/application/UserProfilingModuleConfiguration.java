package com.mysmarthome.userprofiling.application;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"com.mysmarthome.userprofiling.*"})
@EnableJpaRepositories(basePackages = {"com.mysmarthome.userprofiling.*"})
@EntityScan(basePackages = {"com.mysmarthome.userprofiling.*"})
@PropertySource({"classpath:user-profiling-application.properties", "classpath:user-profiling-application-local.properties"})
public class UserProfilingModuleConfiguration {

}
