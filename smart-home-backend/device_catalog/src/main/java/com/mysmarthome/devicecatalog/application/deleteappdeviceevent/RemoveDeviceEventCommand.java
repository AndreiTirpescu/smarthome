package com.mysmarthome.devicecatalog.application.deleteappdeviceevent;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RemoveDeviceEventCommand(@NotEmpty String deviceId, @NotNull Integer eventCode) {
}
