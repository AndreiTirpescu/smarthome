package com.mysmarthome.web.security;

import com.mysmarthome.devicecatalog.application.config.DeviceCatalogSecurityFilters;
import com.mysmarthome.eventstore.application.config.EventStoreSecurityFilters;
import com.mysmarthome.homesystemmanagement.application.config.HomeSystemManagementSecurityFilters;
import com.mysmarthome.identityandaccess.application.config.IamSecurityFilters;
import com.mysmarthome.userprofiling.application.security.UserProfilingSecurityFilters;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${security.whiteLabels}")
    private String[] whiteLabels;

    private final SecurityCorsConfiguration corsConfig;

    private final TokenAuthFilter tokenAuthFilter;

    private final CustomSecurityExceptionHandler customSecurityExceptionHandler;

    private final IamSecurityFilters iamSecurityFilters;

    private final DeviceCatalogSecurityFilters deviceCatalogSecurityFilters;

    private final EventStoreSecurityFilters eventStoreSecurityFilters;

    private final HomeSystemManagementSecurityFilters homeSystemManagementSecurityFilters;

    private final UserProfilingSecurityFilters userProfilingSecurityFilters;

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        var http = httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(request -> buildCorsConfig()))
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(manager -> manager.requestMatchers(whiteLabels).permitAll())
                .addFilterBefore(tokenAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(configurer -> configurer.accessDeniedHandler(customSecurityExceptionHandler))
                .exceptionHandling(configurer -> configurer.authenticationEntryPoint(customSecurityExceptionHandler));

        http = iamSecurityFilters.withIamFilters(http);
        http = deviceCatalogSecurityFilters.withDeviceCatalogFilters(http);
        http = eventStoreSecurityFilters.withEventStoreSecurityFilters(http);
        http = homeSystemManagementSecurityFilters.withHomeSystemManagementFilters(http);
        http = userProfilingSecurityFilters.withUserProfilingSecurityFilters(http);

        return http.build();
    }

    private CorsConfiguration buildCorsConfig() {
        var config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.stream(corsConfig.getAllowedOrigins()).toList());
        config.setAllowedMethods(Arrays.stream(corsConfig.getAllowedMethods()).toList());
        config.setAllowedHeaders(Arrays.stream(corsConfig.getAllowedHeaders()).toList());

        return config;
    }

    @Bean
    public OpenAPI configureOpenApiAuthorization() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes(
                        "TokenAuthorization",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                        )
                );
    }
}
