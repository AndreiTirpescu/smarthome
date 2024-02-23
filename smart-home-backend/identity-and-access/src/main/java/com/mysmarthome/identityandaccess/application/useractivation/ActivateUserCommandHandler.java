package com.mysmarthome.identityandaccess.application.useractivation;

import com.mysmarthome.exceptions.SmartHomeException;
import com.mysmarthome.identityandaccess.application.dtos.UserResponse;
import com.mysmarthome.identityandaccess.application.mappers.UserResponseMapper;
import com.mysmarthome.identityandaccess.domain.infrastructure.IDomainEventPublisher;
import com.mysmarthome.identityandaccess.domain.infrastructure.IUserRepository;
import com.mysmarthome.identityandaccess.domain.valueobjects.UserId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mysmarthome.identityandaccess.application.exceptions.ApplicationExceptionCode.IamUserNotFoundById;

@Service
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/users")
@Tag(name = "users")
public class ActivateUserCommandHandler {

    private final IUserRepository userRepository;

    private final IDomainEventPublisher eventPublisher;

    private final UserResponseMapper mapper;

    @PatchMapping("/{id}/activation")
    @Transactional
    @Operation(security = {@SecurityRequirement(name = "TokenAuthorization")})
    public UserResponse handle(@Valid @RequestBody ActivateUserCommand command,
                               @PathVariable("id") String userId) {
        var fullCommand = command.withUserId(userId);

        var userToBeActivated = userRepository.findById(new UserId(fullCommand.userId()))
                .orElseThrow(() -> new SmartHomeException(IamUserNotFoundById));

        userToBeActivated.activateAccountUsing(command.activationToken());

        eventPublisher.publishDomainEventsFor(userToBeActivated);

        return mapper.responseFrom(userToBeActivated);
    }
}
