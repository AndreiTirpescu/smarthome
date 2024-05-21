package com.mysmarthome.homesystemmanagement.application.dtos;

import java.util.List;

public record PagedConnectedDeviceResponse(List<DeviceResponse> devices, Long count, Integer totalPages) {
}
