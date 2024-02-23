package com.mysmarthome.devicecatalog.application.mappers;

import com.mysmarthome.devicecatalog.application.dtos.DeviceResponse;
import com.mysmarthome.devicecatalog.domain.aggregate.Device;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DeviceResponseMapper {

    @Mappings({
            @Mapping(expression = "java(device.getId().toString())", target = "id"),
    })
    DeviceResponse responseFrom(Device device);
}