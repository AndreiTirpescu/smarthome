package com.mysmarthome.userprofiling.application.dtos;

public record UserProfileResponse(String id,
                                  String identityId,
                                  String email,
                                  String tag,
                                  String profileImageUrl,
                                  UserProfilePersonDetails personalDetails,
                                  UserProfileAddress address) {
}
