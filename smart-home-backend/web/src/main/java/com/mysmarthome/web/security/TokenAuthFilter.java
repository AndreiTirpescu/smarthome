package com.mysmarthome.web.security;

import com.mysmarthome.exceptions.SmartHomeException;
import com.mysmarthome.identityandaccess.application.userauthorization.UserTokenAuthorizationCommand;
import com.mysmarthome.identityandaccess.application.userauthorization.UserTokenAuthorizationCommandHandler;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class TokenAuthFilter extends OncePerRequestFilter {

    private final UserTokenAuthorizationCommandHandler iamTokenAuthHandler;

    @Value("${security.pendingActivationWhiteLabels}")
    private String[] pendingActivationWhiteLabels;

    @Override
    @SneakyThrows
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) {
        try {
            var requestToken = request.getHeader("Authorization");

            var accessCommand = new UserTokenAuthorizationCommand(requestToken, "ACTIVE");
            if (Arrays.stream(pendingActivationWhiteLabels).anyMatch(path -> new AntPathRequestMatcher(path).matches(request))) {
                accessCommand = accessCommand.withAllowedStatus("PENDING_ACTIVATION");
            }

            var tokenDetails = iamTokenAuthHandler.handle(accessCommand);

            var authorities = List.of(
                    new SimpleGrantedAuthority(tokenDetails.role().name()),
                    new SimpleGrantedAuthority(tokenDetails.status().name())
            );

            var authentication = new UsernamePasswordAuthenticationToken(tokenDetails, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } catch (SmartHomeException ex) {
            filterChain.doFilter(request, response);
        }
    }
}
