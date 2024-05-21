package com.mysmarthome.homesystemmanagement.application.dtos;

public record DeviceResponse(String homeSystemId,
                             String deviceCatalogId,
                             Long currentValue,
                             String deviceName,
                             Long connectedAt,
                             Long valueUpdatedAt) {
}
