package com.mysmarthome.devicecatalog.application.adddevicevalue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AddDeviceValueCommand(@JsonIgnore @Schema(hidden = true) String deviceId,
                                    @NotEmpty String label,
                                    @NotNull Integer code,
                                    @NotEmpty String icon,
                                    @NotEmpty String type,
                                    Long rangeMin,
                                    Long rangeMax) {

    public AddDeviceValueCommand withDeviceId(String deviceId) {
        return new AddDeviceValueCommand(deviceId, label, code, icon, type, rangeMin, rangeMax);
    }
}
