package com.mysmarthome.devicecatalog.application.devicecreation;

import com.mysmarthome.devicecatalog.application.dtos.DeviceResponse;
import com.mysmarthome.devicecatalog.application.mappers.DeviceResponseMapper;
import com.mysmarthome.devicecatalog.domain.aggregate.Device;
import com.mysmarthome.devicecatalog.domain.infrastructure.IDeviceRepository;
import com.mysmarthome.devicecatalog.domain.infrastructure.IDomainEventPublisher;
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

@Service
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/devices")
@Tag(name = "device-catalog")
public class CreateDeviceCommandHandler {

    private final IDeviceRepository deviceRepository;

    private final IDomainEventPublisher eventPublisher;

    private final DeviceResponseMapper mapper;

    @PostMapping
    @Operation(security = {@SecurityRequirement(name = "TokenAuthorization")})
    @Transactional
    public DeviceResponse handle(@RequestBody @Valid CreateDeviceCommand command) {
        var device = Device.newDeviceType(
                deviceRepository.nextIdentity(),
                command.name(),
                command.shortDescription(),
                command.description(),
                ""
        );

        deviceRepository.save(device);

        eventPublisher.publishDomainEventsFor(device);

        return mapper.responseFrom(device);
    }
}
