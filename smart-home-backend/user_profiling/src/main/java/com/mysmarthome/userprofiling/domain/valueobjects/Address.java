package com.mysmarthome.userprofiling.domain.valueobjects;

import com.mysmarthome.exceptions.SmartHomeException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Locale;

import static com.mysmarthome.userprofiling.domain.exceptions.DomainExceptionCodes.UnknownCountryCode;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@Getter
@Embeddable
public class Address implements Serializable {
    private String countryCode;

    private String line1;

    private String line2;

    private String city;

    private String county;

    private String postalCode;

    public Address(String countryCode, String line1, String line2, String city, String county, String postalCode) {
        if (countryCode != null && !Arrays.asList(Locale.getISOCountries()).contains(countryCode)) {
            throw new SmartHomeException(UnknownCountryCode);
        }

        this.countryCode = countryCode;
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.county = county;
        this.postalCode = postalCode;
    }

    public static Address emptyAddress() {
        return new Address(null, null, null, null, null, null);
    }
}
