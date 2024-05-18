package com.mysmarthome.userprofiling.application.mappers;

import com.mysmarthome.userprofiling.application.dtos.UserProfileAddress;
import com.mysmarthome.userprofiling.application.dtos.UserProfilePersonDetails;
import com.mysmarthome.userprofiling.application.dtos.UserProfileResponse;
import com.mysmarthome.userprofiling.domain.aggregate.UserProfile;
import com.mysmarthome.userprofiling.domain.valueobjects.Address;
import com.mysmarthome.userprofiling.domain.valueobjects.PersonaDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserProfileResponseMapper {


    @Mapping(target = "country", source = "countryCode")
    @Mapping(target = "addressLine1", source = "line1")
    @Mapping(target = "addressLine2", source = "line2")
    UserProfileAddress toUserProfileAddress(Address address);

    UserProfilePersonDetails toUserPersonDetails(PersonaDetails personalDetails);

    @Mapping(target = "id", expression = "java(userProfile.getId().toString())")
    @Mapping(target = "identityId", expression = "java(userProfile.getIdentity().id())")
    @Mapping(target = "email", expression = "java(userProfile.getIdentity().email())")
    @Mapping(target = "tag", expression = "java(userProfile.getTag().toString())")
    UserProfileResponse toUserProfileResponse(UserProfile userProfile);
}
