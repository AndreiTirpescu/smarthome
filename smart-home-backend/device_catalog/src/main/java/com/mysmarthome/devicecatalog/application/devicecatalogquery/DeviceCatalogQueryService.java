package com.mysmarthome.devicecatalog.application.devicecatalogquery;

import com.mysmarthome.devicecatalog.application.dtos.DeviceEventResponse;
import com.mysmarthome.devicecatalog.application.dtos.DeviceResponse;
import com.mysmarthome.devicecatalog.application.dtos.PagedDeviceResponse;
import com.mysmarthome.devicecatalog.application.mappers.DeviceResponseMapper;
import com.mysmarthome.devicecatalog.domain.infrastructure.IDeviceRepository;
import com.mysmarthome.devicecatalog.domain.valueobjects.DeviceId;
import com.mysmarthome.exceptions.SmartHomeException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.mysmarthome.devicecatalog.application.exceptions.DeviceCatalogApplicationExceptionCode.DeviceCatalogDeviceNotFoundById;
import static com.mysmarthome.devicecatalog.application.exceptions.DeviceCatalogApplicationExceptionCode.DeviceCatalogDeviceNotFoundByTypeCode;

@Service
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/devices")
@Tag(name = "device-catalog")
public class DeviceCatalogQueryService {

    private final IDeviceRepository deviceRepository;

    private final DeviceResponseMapper responseMapper;

    @GetMapping("/{id}")
    @Operation(security = {@SecurityRequirement(name = "TokenAuthorization")})
    public DeviceResponse getById(@PathVariable String id) {
        var device = deviceRepository.byId(new DeviceId(id))
                .orElseThrow(() -> new SmartHomeException(DeviceCatalogDeviceNotFoundById));

        return responseMapper.responseFrom(device);
    }

    @GetMapping("/{id}/events")
    @Operation(security = {@SecurityRequirement(name = "TokenAuthorization")})
    public List<DeviceEventResponse> getAllByDeviceId(@PathVariable String id) {
        var device = deviceRepository.byId(new DeviceId(id))
                .orElseThrow(() -> new SmartHomeException(DeviceCatalogDeviceNotFoundById));

        return responseMapper.responseFrom(device.getDeviceEvents());
    }

    @Operation(security = {@SecurityRequirement(name = "TokenAuthorization")})
    @GetMapping
    public PagedDeviceResponse getByTypeCode(@RequestParam(defaultValue = "0") Integer pageNumber,
                                             @RequestParam(defaultValue = "100") Integer pageSize) {

        var devices = deviceRepository.findAllPaged(pageNumber, pageSize);

        return responseMapper.responseFrom(devices);
    }

    @Operation(security = {@SecurityRequirement(name = "TokenAuthorization")})
    @GetMapping("/typecode/{typecode}")
    public DeviceResponse getByTypeCode(@PathVariable("typecode") String typeCode) {

        var device = deviceRepository.byTypeCode(typeCode)
                .orElseThrow(() -> new SmartHomeException(DeviceCatalogDeviceNotFoundByTypeCode));

        return responseMapper.responseFrom(device);
    }
}
