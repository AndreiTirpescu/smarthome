package com.mysmarthome.identityandaccess.application.userauthorization;

import com.mysmarthome.exceptions.SmartHomeException;
import com.mysmarthome.identityandaccess.application.dtos.TokenValidationResponse;
import com.mysmarthome.identityandaccess.application.exceptions.ApplicationExceptionCode;
import com.mysmarthome.identityandaccess.domain.infrastructure.ITokenGenerator;
import com.mysmarthome.identityandaccess.domain.infrastructure.ITokenValidator;
import com.mysmarthome.identityandaccess.domain.infrastructure.IUserRepository;
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
public class UserAuthorizationCommandHandler {

    private final ITokenGenerator generator;

    private final ITokenValidator validator;

    private final IUserRepository repository;

    @PostMapping("/token/verify")
    public TokenValidationResponse handle(@Valid @RequestBody UserAuthorizationCommand command) {
        boolean isTokenValid = validator.isValid(command.accessToken());

        if (!isTokenValid) {
            throw new SmartHomeException(ApplicationExceptionCode.IamUnauthorizedException);
        }

        var user = generator.extractUserDescriptorFromToken(command.accessToken());

        var dbUser = repository.findById(user.id()).orElseThrow(
                () -> new SmartHomeException(ApplicationExceptionCode.IamUnauthorizedException)
        );

        if (dbUser.getStatus() == UserStatus.INACTIVE) {
            throw new SmartHomeException(ApplicationExceptionCode.IamUnauthorizedException);
        }

        if (dbUser.getStatus() == UserStatus.PENDING_ACTIVATION) {
            throw new SmartHomeException(ApplicationExceptionCode.IamUserForbidden);
        }

        return new TokenValidationResponse(user.id().toString(), user.role().name());
    }
}
