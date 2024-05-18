package com.mysmarthome.devicecatalog.infrastructure.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class StaticServerClientConfiguration {

    @Value("${static.server.address}")
    private String staticServerBaseUrl;

    @Bean("staticServerWebClient")
    public WebClient staticServerWebClient() {
        return WebClient.builder()
                .baseUrl(staticServerBaseUrl)
                .build();
    }
}
