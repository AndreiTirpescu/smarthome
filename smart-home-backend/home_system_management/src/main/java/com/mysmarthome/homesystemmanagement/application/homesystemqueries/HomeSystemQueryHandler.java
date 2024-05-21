package com.mysmarthome.homesystemmanagement.application.homesystemqueries;

import com.mysmarthome.exceptions.SmartHomeException;
import com.mysmarthome.homesystemmanagement.application.dtos.DeviceResponse;
import com.mysmarthome.homesystemmanagement.application.dtos.HomeSystemResponse;
import com.mysmarthome.homesystemmanagement.application.dtos.PagedConnectedDeviceResponse;
import com.mysmarthome.homesystemmanagement.application.mappers.HomeSystemResponseMapper;
import com.mysmarthome.homesystemmanagement.domain.aggregate.HomeSystem;
import com.mysmarthome.homesystemmanagement.domain.infrastructure.IHomeSystemRepository;
import com.mysmarthome.homesystemmanagement.domain.valueobjects.HomeSystemId;
import com.mysmarthome.homesystemmanagement.domain.valueobjects.Identity;
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

import static com.mysmarthome.homesystemmanagement.application.exceptions.ApplicationExceptionCodes.HomeSystemNotFoundById;

@Service
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/homesystems")
@Tag(name = "home-systems")
public class HomeSystemQueryHandler {
    private final IHomeSystemRepository repository;

    private final HomeSystemResponseMapper responseMapper;

    @GetMapping("/{id}")
    @Operation(security = {@SecurityRequirement(name = "TokenAuthorization")})
    public HomeSystemResponse findHomeSystemById(@PathVariable String id) {
        var homesystem = repository.findById(new HomeSystemId(id))
                .orElseThrow(() -> new SmartHomeException(HomeSystemNotFoundById));

        return responseMapper.toHomeSystemResponse(homesystem);
    }

    @GetMapping("/identity/{id}")
    @Operation(security = {@SecurityRequirement(name = "TokenAuthorization")})
    public HomeSystemResponse findByIdentityId(@PathVariable String id) {
        var homesystem = repository.findByIdentity(new Identity(id))
                .orElseThrow(() -> new SmartHomeException(HomeSystemNotFoundById));

        return responseMapper.toHomeSystemResponse(homesystem);
    }

    @GetMapping("/{id}/devices")
    @Operation(security = {@SecurityRequirement(name = "TokenAuthorization")})
    public PagedConnectedDeviceResponse findConnectedDevicesByHomeSystemId(@PathVariable String id,
                                                                           @RequestParam(defaultValue = "0") Integer pageNumber,
                                                                           @RequestParam(defaultValue = "100") Integer pageSize) {
        var homesystem = repository.findById(new HomeSystemId(id))
                .orElseThrow(() -> new SmartHomeException(HomeSystemNotFoundById));

        var devices = repository.devicesForHomeSystem(homesystem, pageNumber, pageSize);

        return new PagedConnectedDeviceResponse(
                responseMapper.toDeviceResponseList(devices.data()),
                devices.count(),
                devices.totalPages()
        );
    }
}
