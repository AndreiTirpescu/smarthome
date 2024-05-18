package com.mysmarthome.userprofiling.application.updateprofileaddress;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

public record UpdateProfileAddress(@NotEmpty String countryCode,
                                   String addressLine1,
                                   String addressLine2,
                                   String city,
                                   String county,
                                   @NumberFormat String postalCode) {
}
