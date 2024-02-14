package com.mysmarthome.web.security;

import com.mysmarthome.identityandaccess.application.config.IamSecurityFilters;
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

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${security.whiteLabels}")
    private String[] whiteLabels;

    private final TokenAuthFilter tokenAuthFilter;

    private final CustomSecurityExceptionHandler customSecurityExceptionHandler;

    private final IamSecurityFilters iamSecurityFilters;

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        var http = httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(manager -> manager.requestMatchers(whiteLabels).permitAll())
                .addFilterBefore(tokenAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(configurer -> configurer.accessDeniedHandler(customSecurityExceptionHandler))
                .exceptionHandling(configurer -> configurer.authenticationEntryPoint(customSecurityExceptionHandler));

        http = iamSecurityFilters.withIamFilters(http);

        return http.build();
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
