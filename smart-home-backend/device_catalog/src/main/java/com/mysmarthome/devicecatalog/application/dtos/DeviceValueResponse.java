package com.mysmarthome.devicecatalog.application.dtos;

public record DeviceValueResponse(String deviceId, String label, Integer code, String type, Long rangeMin, Long rangeMax) {
}
