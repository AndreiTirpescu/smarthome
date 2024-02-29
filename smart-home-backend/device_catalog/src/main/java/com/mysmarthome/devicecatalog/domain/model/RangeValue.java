package com.mysmarthome.devicecatalog.domain.model;

import com.mysmarthome.devicecatalog.domain.aggregate.Device;
import com.mysmarthome.devicecatalog.domain.valueobjects.ValueRange;
import com.mysmarthome.devicecatalog.domain.valueobjects.ValueType;
import com.mysmarthome.exceptions.SmartHomeException;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.mysmarthome.devicecatalog.domain.exceptions.DeviceCatalogExceptionCode.DeviceCatalogDeviceValueTypeRangeMissing;

@Entity
@DiscriminatorValue("RANGE")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class RangeValue extends DeviceValue {
    RangeValue(Device device, @NotNull String label, int code, @NotNull String icon, ValueRange range) {
        super(device, label, code, icon, ValueType.RANGE, range);

        if (range == null) {
            throw new SmartHomeException(DeviceCatalogDeviceValueTypeRangeMissing);
        }
    }
}
