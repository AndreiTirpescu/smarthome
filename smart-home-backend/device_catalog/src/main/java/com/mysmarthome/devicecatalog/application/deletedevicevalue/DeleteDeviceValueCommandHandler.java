package com.mysmarthome.devicecatalog.application.deletedevicevalue;

import com.mysmarthome.devicecatalog.domain.infrastructure.IDeviceRepository;
import com.mysmarthome.devicecatalog.domain.infrastructure.IDomainEventPublisher;
import com.mysmarthome.devicecatalog.domain.valueobjects.DeviceId;
import com.mysmarthome.exceptions.SmartHomeException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mysmarthome.devicecatalog.application.exceptions.DeviceCatalogApplicationExceptionCode.DeviceCatalogDeviceNotFoundById;

@Service
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/devices")
@Tag(name = "device-catalog")
public class DeleteDeviceValueCommandHandler {

    private final IDeviceRepository deviceRepository;

    private final IDomainEventPublisher eventPublisher;

    @DeleteMapping("/{id}/values/{code}")
    @Transactional
    @Operation(security = {@SecurityRequirement(name = "TokenAuthorization")})
    public Integer handle(@Valid @RequestBody DeleteDeviceValueCommand command, @PathVariable String id) {
        var cmd = command.withDeviceId(id);

        var device = deviceRepository.byId(new DeviceId(cmd.deviceId()))
                .orElseThrow(()-> new SmartHomeException(DeviceCatalogDeviceNotFoundById));

        device.removeValueByCode(command.code());

        eventPublisher.publishDomainEventsFor(device);

        return command.code();
    }
}
