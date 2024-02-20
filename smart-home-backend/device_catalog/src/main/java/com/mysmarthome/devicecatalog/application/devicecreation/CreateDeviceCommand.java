package com.mysmarthome.devicecatalog.application.devicecreation;

import jakarta.validation.constraints.NotEmpty;

public record CreateDeviceCommand(@NotEmpty String name, @NotEmpty String description) {
}
