package com.mysmarthome.homesystemmanagement.application.addhomesystem;

import com.mysmarthome.exceptions.SmartHomeException;
import com.mysmarthome.homesystemmanagement.application.dtos.HomeSystemResponse;
import com.mysmarthome.homesystemmanagement.application.mappers.HomeSystemResponseMapper;
import com.mysmarthome.homesystemmanagement.domain.aggregate.HomeSystem;
import com.mysmarthome.homesystemmanagement.domain.infrastructure.IDomainEventsPublisher;
import com.mysmarthome.homesystemmanagement.domain.infrastructure.IHomeSystemRepository;
import com.mysmarthome.homesystemmanagement.domain.valueobjects.Identity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mysmarthome.homesystemmanagement.application.exceptions.ApplicationExceptionCodes.UserAlreadyHasAHomeSystem;

@Service
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/homesystems")
@Tag(name = "home-systems")
public class ConnectHomeSystemCommandHandler {

    private final IHomeSystemRepository homeSystemRepository;

    private final IDomainEventsPublisher eventsPublisher;

    private final HomeSystemResponseMapper responseMapper;

    @Transactional
    @PostMapping
    @Operation(security = {@SecurityRequirement(name = "TokenAuthorization")})
    public HomeSystemResponse handle(@RequestBody @Valid ConnectHomeSystemCommand command) {
        if (homeSystemRepository.findByIdentity(new Identity(command.identityId())).isPresent()) {
            throw new SmartHomeException(UserAlreadyHasAHomeSystem);
        }

        var newlyConnected = HomeSystem.connectNewHomeSystem(
                homeSystemRepository.nextIdentity(),
                command.identityId(),
                command.name()
        );

        homeSystemRepository.save(newlyConnected);
        eventsPublisher.publishDomainEventsFor(newlyConnected);

        return responseMapper.toHomeSystemResponse(newlyConnected);
    }
}
