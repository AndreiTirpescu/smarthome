package com.mysmarthome.devicecatalog.application.adddeviceevents;

import com.mysmarthome.devicecatalog.application.dtos.DeviceEventResponse;
import com.mysmarthome.devicecatalog.application.mappers.DeviceResponseMapper;
import com.mysmarthome.devicecatalog.domain.infrastructure.IDeviceEventsRepository;
import com.mysmarthome.devicecatalog.domain.infrastructure.IDeviceRepository;
import com.mysmarthome.devicecatalog.domain.valueobjects.DeviceId;
import com.mysmarthome.exceptions.SmartHomeException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mysmarthome.devicecatalog.application.exceptions.DeviceCatalogApplicationExceptionCode.DeviceCatalogDeviceEventCodeAlreadyUsed;
import static com.mysmarthome.devicecatalog.application.exceptions.DeviceCatalogApplicationExceptionCode.DeviceCatalogDeviceNotFoundById;

@Service
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/devices")
@Tag(name = "device-catalog")
public class AddDeviceEventCommandHandler {

    private final IDeviceRepository repository;

    private final IDeviceEventsRepository eventsRepository;

    private final DeviceResponseMapper mapper;

    @PostMapping("/{id}/events")
    @Transactional
    @Operation(security = {@SecurityRequirement(name = "TokenAuthorization")})
    public DeviceEventResponse handle(@Valid @RequestBody AddDeviceEventCommand command, @PathVariable String id) {
        var addCommandComplete = command.withDeviceId(id);

        var device = repository.byId(new DeviceId(addCommandComplete.deviceId()))
                .orElseThrow(() -> new SmartHomeException(DeviceCatalogDeviceNotFoundById));

        if (eventsRepository.existsEventByDeviceIdAndCode(device.getId(), command.code())) {
            throw new SmartHomeException(DeviceCatalogDeviceEventCodeAlreadyUsed);
        }

        var deviceEvent = device.addEventType(addCommandComplete.name(), addCommandComplete.code());

        return mapper.responseFrom(deviceEvent);
    }
}
