package com.mysmarthome.web.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.cors")
@Data
public class SecurityCorsConfiguration {
    private String[] allowedOrigins;

    private String[] allowedHeaders;

    private String[] allowedMethods;
}
