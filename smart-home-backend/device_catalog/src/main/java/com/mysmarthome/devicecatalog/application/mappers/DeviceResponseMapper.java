package com.mysmarthome.devicecatalog.application.mappers;

import com.mysmarthome.devicecatalog.application.dtos.DeviceEventResponse;
import com.mysmarthome.devicecatalog.application.dtos.DeviceResponse;
import com.mysmarthome.devicecatalog.application.dtos.PagedDeviceResponse;
import com.mysmarthome.devicecatalog.domain.aggregate.Device;
import com.mysmarthome.devicecatalog.domain.model.DeviceEvent;
import com.mysmarthome.domain.PagedView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

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

    @Mappings(
            @Mapping(source = "data", target = "devices")
    )
    PagedDeviceResponse responseFrom(PagedView<Device> devicePagedView);

    List<DeviceEventResponse> responseFrom(List<DeviceEvent> events);
}
