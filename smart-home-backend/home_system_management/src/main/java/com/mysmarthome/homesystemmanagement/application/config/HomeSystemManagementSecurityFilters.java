package com.mysmarthome.homesystemmanagement.application.config;

import lombok.SneakyThrows;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

@Component("homeSystemManagementSecurityFilters")
public class HomeSystemManagementSecurityFilters {

    @SneakyThrows
    public HttpSecurity withHomeSystemManagementFilters(HttpSecurity http) {
        http.authorizeHttpRequests(requests ->
                requests.requestMatchers(
                        "/v1/homesystems/**", "/v1/homesystems", "/v1/homesystems**").authenticated()
        );

        return http;
    }
}
