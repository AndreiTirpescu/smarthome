package com.mysmarthome.identityandaccess.application.config;

import lombok.SneakyThrows;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

@Component("iamSecurityConfig")
public class IamSecurityFilters {

    @SneakyThrows
    public HttpSecurity withIamFilters(HttpSecurity http) {

        http.authorizeHttpRequests(requests ->
                requests.requestMatchers(HttpMethod.POST, "/v1/users", "/v1/auth/login", "/v1/auth/token/verify").permitAll()
                        .requestMatchers( "/v1/users/**", "/v1/users", "/v1/users**").authenticated()
        );

        return http;
    }
}
