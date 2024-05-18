package com.mysmarthome.userprofiling.application.security;

import lombok.SneakyThrows;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

@Component("userProfilingSecurityConfig")
public class UserProfilingSecurityFilters {

    @SneakyThrows
    public HttpSecurity withUserProfilingSecurityFilters(HttpSecurity http) {

        http.authorizeHttpRequests(requests ->
                requests.requestMatchers( "/v1/profiles/**", "/v1/profiles", "/v1/profiles**").authenticated()
        );

        return http;
    }
}
