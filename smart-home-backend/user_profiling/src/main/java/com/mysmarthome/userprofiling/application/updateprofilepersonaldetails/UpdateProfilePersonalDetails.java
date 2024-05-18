package com.mysmarthome.userprofiling.application.updateprofilepersonaldetails;

import jakarta.validation.constraints.NotEmpty;

public record UpdateProfilePersonalDetails(@NotEmpty String firstName,
                                           @NotEmpty String lastName,
                                           String middleName) {
}
