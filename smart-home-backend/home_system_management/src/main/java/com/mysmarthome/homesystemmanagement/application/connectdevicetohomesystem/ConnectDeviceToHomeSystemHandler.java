package com.mysmarthome.homesystemmanagement.application.connectdevicetohomesystem;

import com.mysmarthome.exceptions.SmartHomeException;
import com.mysmarthome.homesystemmanagement.application.dtos.DeviceResponse;
import com.mysmarthome.homesystemmanagement.application.mappers.HomeSystemResponseMapper;
import com.mysmarthome.homesystemmanagement.domain.infrastructure.IDomainEventsPublisher;
import com.mysmarthome.homesystemmanagement.domain.infrastructure.IHomeSystemRepository;
import com.mysmarthome.homesystemmanagement.domain.valueobjects.HomeSystemId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mysmarthome.homesystemmanagement.application.exceptions.ApplicationExceptionCodes.HomeSystemNotFoundById;

@Service
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/homesystems")
@Tag(name = "home-systems")
public class ConnectDeviceToHomeSystemHandler {

    private final IHomeSystemRepository homeSystemRepository;

    private final IDomainEventsPublisher eventsPublisher;

    private final HomeSystemResponseMapper responseMapper;

    @Transactional
    @PostMapping("/{id}/devices")
    @Operation(security = {@SecurityRequirement(name = "TokenAuthorization")})
    public DeviceResponse handle(@RequestBody @Valid ConnectDeviceToHomeSystem command, @PathVariable String id) {
        var homeSystem = homeSystemRepository.findById(new HomeSystemId(id))
                .orElseThrow(() -> new SmartHomeException(HomeSystemNotFoundById));

        var newlyConnectedDevice = homeSystem.connectNewDeviceToHomeSystem(
                command.deviceCatalogId(),
                command.deviceName()
        );

        eventsPublisher.publishDomainEventsFor(homeSystem);

        return responseMapper.toDeviceResponse(newlyConnectedDevice);
    }
}
