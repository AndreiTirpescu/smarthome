package com.mysmarthome.identityandaccess.application.userauthorization;

import com.mysmarthome.exceptions.SmartHomeException;
import com.mysmarthome.identityandaccess.application.dtos.TokenAuthorizationResponse;
import com.mysmarthome.identityandaccess.application.exceptions.ApplicationExceptionCode;
import com.mysmarthome.identityandaccess.application.mappers.TokenResponseMapper;
import com.mysmarthome.identityandaccess.domain.infrastructure.ITokenGenerator;
import com.mysmarthome.identityandaccess.domain.valueobjects.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserTokenAuthorizationCommandHandler {

    private final ITokenGenerator generator;

    private final TokenResponseMapper mapper;


    public TokenAuthorizationResponse handle(UserTokenAuthorizationCommand command) {
        var token = command.accessToken();

        if (token == null || !token.startsWith("Bearer ")) {
            throw new SmartHomeException(ApplicationExceptionCode.IamUnauthorizedException);
        }

        try {
            var descriptor = generator.extractUserDescriptorFromToken(token.replace("Bearer ", ""));

            if (UserStatus.ACTIVE != descriptor.status()) {
                throw new SmartHomeException(ApplicationExceptionCode.IamUserForbidden);
            }

            return mapper.responseFrom(descriptor);
        } catch (SmartHomeException ex) {
            throw ex;
        } catch (RuntimeException ex) {
            throw new SmartHomeException(ApplicationExceptionCode.IamUserForbidden);
        }
    }
}
