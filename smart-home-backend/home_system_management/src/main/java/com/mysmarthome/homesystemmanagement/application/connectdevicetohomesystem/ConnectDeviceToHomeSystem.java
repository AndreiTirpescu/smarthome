package com.mysmarthome.homesystemmanagement.application.connectdevicetohomesystem;

import jakarta.validation.constraints.NotEmpty;

public record ConnectDeviceToHomeSystem(@NotEmpty(message = "Device must exist in catalog") String deviceCatalogId,
                                        @NotEmpty(message = "Device must have a name") String deviceName) {
}
