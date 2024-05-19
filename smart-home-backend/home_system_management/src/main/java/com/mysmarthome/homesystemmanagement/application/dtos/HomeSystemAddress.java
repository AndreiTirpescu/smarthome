package com.mysmarthome.homesystemmanagement.application.dtos;

public record UserProfileAddress(String country,
                                 String addressLine1,
                                 String addressLine2,
                                 String county,
                                 String city,
                                 String postalCode) {
}
