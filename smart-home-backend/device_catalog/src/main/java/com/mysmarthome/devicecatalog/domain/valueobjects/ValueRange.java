package com.mysmarthome.devicecatalog.domain.valueobjects;

import com.mysmarthome.devicecatalog.domain.exceptions.DeviceCatalogExceptionCode;
import com.mysmarthome.exceptions.SmartHomeException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@With
public class ValueRange implements Serializable {

    private Long rangeMin;

    private Long rangeMax;

    public ValueRange(Long rangeMin, Long rangeMax) {
        if (rangeMin == null || rangeMax == null || rangeMax < rangeMin) {
            throw new SmartHomeException(DeviceCatalogExceptionCode.DeviceCatalogInvalidDeviceValueRange);
        }

        this.rangeMin = rangeMin;
        this.rangeMax = rangeMax;
    }

    public Long min() {
        return rangeMin;
    }

    public Long max() {
        return rangeMax;
    }
}
