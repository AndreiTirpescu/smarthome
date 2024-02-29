package com.mysmarthome.devicecatalog.application.adddevicevalue;

import com.mysmarthome.devicecatalog.application.dtos.DeviceValueResponse;
import com.mysmarthome.devicecatalog.application.mappers.DeviceResponseMapper;
import com.mysmarthome.devicecatalog.domain.infrastructure.IDeviceRepository;
import com.mysmarthome.devicecatalog.domain.infrastructure.IDomainEventPublisher;
import com.mysmarthome.devicecatalog.domain.valueobjects.DeviceId;
import com.mysmarthome.devicecatalog.domain.valueobjects.ValueRange;
import com.mysmarthome.devicecatalog.domain.valueobjects.ValueType;
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

import static com.mysmarthome.devicecatalog.application.exceptions.DeviceCatalogApplicationExceptionCode.DeviceCatalogDeviceNotFoundById;

@Service
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/devices")
@Tag(name = "device-catalog")
public class AddDeviceValueCommandHandler {
    private final IDeviceRepository repository;

    private final DeviceResponseMapper mapper;

    private final IDomainEventPublisher eventPublisher;

    @PostMapping("/{id}/values")
    @Transactional
    @Operation(security = {@SecurityRequirement(name = "TokenAuthorization")})
    public DeviceValueResponse handle(@Valid @RequestBody AddDeviceValueCommand command, @PathVariable String id) {
        var cmd = command.withDeviceId(id);

        var device = repository.byId(new DeviceId(cmd.deviceId()))
                .orElseThrow(() -> new SmartHomeException(DeviceCatalogDeviceNotFoundById));

        var addedValue = device.addDeviceValue(cmd.label(), cmd.code(), cmd.icon(), ValueType.valueOf(cmd.type()),
                cmd.rangeMin() != null && cmd.rangeMax() != null ? new ValueRange(cmd.rangeMin(), cmd.rangeMax()) : null);

        eventPublisher.publishDomainEventsFor(device);

        return mapper.responseFrom(addedValue);
    }
}
