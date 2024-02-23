package com.mysmarthome.identityandaccess.application.userauthorization;

import com.mysmarthome.exceptions.SmartHomeException;
import com.mysmarthome.identityandaccess.application.dtos.TokenResponse;
import com.mysmarthome.identityandaccess.application.mappers.TokenResponseMapper;
import com.mysmarthome.identityandaccess.domain.infrastructure.ITokenGenerator;
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

import static com.mysmarthome.identityandaccess.application.exceptions.ApplicationExceptionCode.IamUnauthorizedException;
import static com.mysmarthome.identityandaccess.application.exceptions.ApplicationExceptionCode.IamUserForbidden;

@Service
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auth")
@Tag(name = "auth")
public class UserRefreshTokenCommandHandler {

    private final ITokenGenerator generator;

    private final IUserRepository repository;

    private final TokenResponseMapper mapper;

    @PostMapping("/token/refresh")
    public TokenResponse handle(@Valid @RequestBody UserRefreshTokenCommand command) {

        var userDescriptor = generator.extractUserDescriptorFromToken(command.refreshToken());

        var user = repository.findById(userDescriptor.id())
                .orElseThrow(() -> new SmartHomeException(IamUnauthorizedException));

        if (user.getStatus() == UserStatus.INACTIVE) {
            throw new SmartHomeException(IamUserForbidden);
        }

        var newTokenPair = generator.generateTokenForUser(user);

        return mapper.responseFrom(newTokenPair);
    }
}
