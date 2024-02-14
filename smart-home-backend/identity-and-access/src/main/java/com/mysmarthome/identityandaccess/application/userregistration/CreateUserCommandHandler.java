package com.mysmarthome.identityandaccess.application.userregistration;

import com.mysmarthome.exceptions.SmartHomeException;
import com.mysmarthome.identityandaccess.application.dtos.UserResponse;
import com.mysmarthome.identityandaccess.application.exceptions.ApplicationExceptionCode;
import com.mysmarthome.identityandaccess.application.mappers.UserResponseMapper;
import com.mysmarthome.identityandaccess.domain.aggregate.User;
import com.mysmarthome.identityandaccess.domain.infrastructure.IDomainEventPublisher;
import com.mysmarthome.identityandaccess.domain.infrastructure.IPasswordEncoder;
import com.mysmarthome.identityandaccess.domain.infrastructure.IUserRepository;
import com.mysmarthome.identityandaccess.domain.valueobjects.EmailAddress;
import com.mysmarthome.identityandaccess.domain.valueobjects.PlainTextPassword;
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
@RequestMapping("/v1/users")
@Tag(name= "users")
public class CreateUserCommandHandler {

    private final IUserRepository userRepository;

    private final IPasswordEncoder passwordEncoder;

    private final IDomainEventPublisher domainEventPublisher;

    private final UserResponseMapper mapper;

    @PostMapping
    public UserResponse handle(@Valid @RequestBody CreateUserCommand createUserCommand) {
        if (userRepository.existsByEmail(new EmailAddress(createUserCommand.email()))) {
            throw new SmartHomeException(ApplicationExceptionCode.IamUserAlreadyExists);
        }

        var email = new EmailAddress(createUserCommand.email());
        var password = passwordEncoder.encodePassword(new PlainTextPassword(createUserCommand.password()));

        User registeredUser = User.standardUser(userRepository.nextIdentity(), email, password);

        userRepository.save(registeredUser);

        domainEventPublisher.publishDomainEventsFor(registeredUser);

        return mapper.responseFrom(registeredUser);
    }
}
