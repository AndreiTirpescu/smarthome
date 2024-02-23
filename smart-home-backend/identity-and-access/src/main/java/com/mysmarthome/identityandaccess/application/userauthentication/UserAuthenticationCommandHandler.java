package com.mysmarthome.identityandaccess.application.userauthentication;

import com.mysmarthome.domain.Event;
import com.mysmarthome.exceptions.SmartHomeException;
import com.mysmarthome.identityandaccess.application.dtos.TokenResponse;
import com.mysmarthome.identityandaccess.application.exceptions.ApplicationExceptionCode;
import com.mysmarthome.identityandaccess.application.mappers.TokenResponseMapper;
import com.mysmarthome.identityandaccess.domain.aggregate.User;
import com.mysmarthome.identityandaccess.domain.events.UserAuthenticatedEvent;
import com.mysmarthome.identityandaccess.domain.infrastructure.IDomainEventPublisher;
import com.mysmarthome.identityandaccess.domain.infrastructure.IPasswordEncoder;
import com.mysmarthome.identityandaccess.domain.infrastructure.ITokenGenerator;
import com.mysmarthome.identityandaccess.domain.infrastructure.IUserRepository;
import com.mysmarthome.identityandaccess.domain.valueobjects.EmailAddress;
import com.mysmarthome.identityandaccess.domain.valueobjects.UserStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auth")
@Tag(name = "auth")
public class UserAuthenticationCommandHandler {

    private final IUserRepository repository;

    private final IPasswordEncoder passwordEncoder;

    private final ITokenGenerator tokenGenerator;

    private final TokenResponseMapper tokenMapper;

    private final IDomainEventPublisher eventPublisher;

    @PostMapping("/login")
    public TokenResponse authenticateUser(@Valid @RequestBody AuthenticateUserCommand command) {

        User toBeAuthenticated = repository.findByEmail(new EmailAddress(command.email())).orElseThrow(
                () -> new SmartHomeException(ApplicationExceptionCode.IamInvalidUserCredentials)
        );

        boolean isPasswordMatch = passwordEncoder.matches(command.password(), toBeAuthenticated.getPassword());

        if (!isPasswordMatch || UserStatus.INACTIVE == toBeAuthenticated.getStatus()) {
            throw new SmartHomeException(ApplicationExceptionCode.IamInvalidUserCredentials);
        }

        var token = tokenGenerator.generateTokenForUser(toBeAuthenticated);

        eventPublisher.publish(
                Event.from(new UserAuthenticatedEvent(toBeAuthenticated.getId().toString())).withIdentity(
                        toBeAuthenticated.getId().toString()
                )
        );

        return tokenMapper.toResponse(token);
    }
}
