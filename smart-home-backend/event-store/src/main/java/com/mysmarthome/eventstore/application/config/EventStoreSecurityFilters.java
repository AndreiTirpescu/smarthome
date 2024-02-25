package com.mysmarthome.eventstore.application.config;

import lombok.SneakyThrows;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

@Component("eventStoreSecurityFilters")
public class EventStoreSecurityFilters {
    @SneakyThrows
    public HttpSecurity withEventStoreSecurityFilters(HttpSecurity http) {
        http.authorizeHttpRequests(requests ->
                requests.requestMatchers( "/v1/events/**", "/v1/events").authenticated()
        );

        return http;
    }
}
