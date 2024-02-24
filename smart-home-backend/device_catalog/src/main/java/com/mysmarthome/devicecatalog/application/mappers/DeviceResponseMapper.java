package com.mysmarthome.devicecatalog.application.mappers;

import com.mysmarthome.devicecatalog.application.dtos.DeviceEventResponse;
import com.mysmarthome.devicecatalog.application.dtos.DeviceResponse;
import com.mysmarthome.devicecatalog.domain.aggregate.Device;
import com.mysmarthome.devicecatalog.domain.valueobjects.DeviceEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DeviceResponseMapper {

    @Mappings({
            @Mapping(expression = "java(device.getId().toString())", target = "id"),
    })
    DeviceResponse responseFrom(Device device);

    @Mappings({
            @Mapping(expression = "java(event.deviceId().toString())", target = "deviceId"),
            @Mapping(expression = "java(event.name())", target = "name"),
            @Mapping(expression = "java(event.code())", target = "code"),
    })
    DeviceEventResponse responseFrom(DeviceEvent event);
}
