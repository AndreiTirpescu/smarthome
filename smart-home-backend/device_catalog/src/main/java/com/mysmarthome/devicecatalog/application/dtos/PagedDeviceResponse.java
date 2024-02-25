package com.mysmarthome.devicecatalog.application.dtos;

import java.util.List;

public record PagedDeviceResponse(List<DeviceResponse> devices, Long count, Integer totalPages) {
}
