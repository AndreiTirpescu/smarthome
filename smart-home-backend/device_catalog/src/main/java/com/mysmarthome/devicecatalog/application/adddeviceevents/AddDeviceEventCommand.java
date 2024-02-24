package com.mysmarthome.devicecatalog.application.adddeviceevents;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AddDeviceEventCommand(@NotEmpty String name, @NotNull Integer code, @JsonIgnore @Schema(hidden = true) String deviceId) {
    public AddDeviceEventCommand withDeviceId(String deviceId) {
        return new AddDeviceEventCommand(name, code, deviceId);
    }
}
