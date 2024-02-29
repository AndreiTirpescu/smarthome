package com.mysmarthome.devicecatalog.domain.model;

import com.mysmarthome.devicecatalog.domain.aggregate.Device;
import com.mysmarthome.devicecatalog.domain.valueobjects.ValueType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("SWITCH")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class SwitchValue extends DeviceValue {
    SwitchValue(Device device, String label, int code, String icon) {
        super(device, label, code, icon, ValueType.SWITCH, null);
    }
}
