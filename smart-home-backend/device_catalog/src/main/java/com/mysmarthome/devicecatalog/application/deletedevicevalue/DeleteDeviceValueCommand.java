package com.mysmarthome.devicecatalog.application.deletedevicevalue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record DeleteDeviceValueCommand(@JsonIgnore @Schema(hidden = true) String deviceId, @NotNull Integer code) {

    public DeleteDeviceValueCommand withDeviceId(String deviceId) {
        return new DeleteDeviceValueCommand(deviceId, code);
    }
}
