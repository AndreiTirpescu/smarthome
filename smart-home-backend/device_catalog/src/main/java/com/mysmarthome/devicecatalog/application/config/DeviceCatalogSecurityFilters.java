package com.mysmarthome.devicecatalog.application.config;

import lombok.SneakyThrows;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

@Component("deviceCatalogSecurityConfig")
public class DeviceCatalogSecurityFilters {

    @SneakyThrows
    public HttpSecurity withDeviceCatalogFilters(HttpSecurity http) {
        http.authorizeHttpRequests(requests ->
                requests
                        .requestMatchers(HttpMethod.GET, "/v1/devices", "/v1/devices**").authenticated()
                        .requestMatchers( "/v1/devices/**", "/v1/devices", "/v1/devices**").hasAuthority("ADMIN")
        );

        return http;
    }
}
