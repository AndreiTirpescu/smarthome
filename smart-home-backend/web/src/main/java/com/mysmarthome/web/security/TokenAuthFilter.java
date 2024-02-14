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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class TokenAuthFilter extends OncePerRequestFilter {

    private final UserTokenAuthorizationCommandHandler iamTokenAuthHandler;

    @Override
    @SneakyThrows
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) {
        try {
            var requestToken = request.getHeader("Authorization");

            var tokenDetails = iamTokenAuthHandler.handle(new UserTokenAuthorizationCommand(requestToken));

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
