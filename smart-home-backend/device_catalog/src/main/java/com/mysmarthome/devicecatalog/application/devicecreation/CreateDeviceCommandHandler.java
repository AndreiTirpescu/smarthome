package com.mysmarthome.devicecatalog.application.devicecreation;

import com.google.gson.Gson;
import com.mysmarthome.devicecatalog.application.dtos.DeviceResponse;
import com.mysmarthome.devicecatalog.application.mappers.DeviceResponseMapper;
import com.mysmarthome.devicecatalog.domain.aggregate.Device;
import com.mysmarthome.devicecatalog.domain.infrastructure.IDeviceImageProvider;
import com.mysmarthome.devicecatalog.domain.infrastructure.IDeviceRepository;
import com.mysmarthome.devicecatalog.domain.infrastructure.IDomainEventPublisher;
import com.mysmarthome.exceptions.SmartHomeException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.mysmarthome.devicecatalog.application.exceptions.DeviceCatalogApplicationExceptionCode.DeviceCatalogDeviceTypeCodeAlreadyUsed;

@Service
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/devices")
@Tag(name = "device-catalog")
public class CreateDeviceCommandHandler {

    private final IDeviceRepository deviceRepository;
    private final IDeviceImageProvider deviceImageProvider;

    private final IDomainEventPublisher eventPublisher;

    private final DeviceResponseMapper mapper;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(security = {@SecurityRequirement(name = "TokenAuthorization")})
    @Transactional
    public DeviceResponse handle(@RequestParam(value = "file") MultipartFile file,
                                 @RequestParam(value = "payload") String payload) {
        var command = new Gson().fromJson(payload, CreateDeviceCommand.class);

        if (deviceRepository.existsByTypeCode(command.typeCode())) {
            throw new SmartHomeException(DeviceCatalogDeviceTypeCodeAlreadyUsed);
        }

        var device = Device.newDeviceType(
                deviceRepository.nextIdentity(),
                command.name(),
                command.typeCode(),
                command.shortDescription(),
                command.description(),
                deviceImageProvider.uploadImageForDevice(file)
        );

        deviceRepository.save(device);

        eventPublisher.publishDomainEventsFor(device);

        return mapper.responseFrom(device);
    }
}
