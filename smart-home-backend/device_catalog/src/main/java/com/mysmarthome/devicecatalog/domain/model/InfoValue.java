package com.mysmarthome.devicecatalog.domain.model;

import com.mysmarthome.devicecatalog.domain.aggregate.Device;
import com.mysmarthome.devicecatalog.domain.valueobjects.ValueType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("INFO")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class InfoValue extends DeviceValue {
    public InfoValue(Device device, String label, int code, String icon) {
        super(device, label, code, icon, ValueType.INFO, null);
    }
}
