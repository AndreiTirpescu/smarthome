package com.mysmarthome.homesystemmanagement.application.mappers;

import com.mysmarthome.homesystemmanagement.application.dtos.HomeSystemAddress;
import com.mysmarthome.homesystemmanagement.application.dtos.HomeSystemResponse;
import com.mysmarthome.homesystemmanagement.domain.aggregate.HomeSystem;
import com.mysmarthome.homesystemmanagement.domain.valueobjects.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HomeSystemResponseMapper {
    @Mapping(target = "country", source = "countryCode")
    @Mapping(target = "addressLine1", source = "line1")
    @Mapping(target = "addressLine2", source = "line2")
    HomeSystemAddress toHomeSystemAddress(Address address);

    @Mapping(target = "id", expression = "java(homeSystem.getHomeSystemId().toString())")
    @Mapping(target = "identityId", expression = "java(homeSystem.getIdentity().id())")
    HomeSystemResponse toHomeSystemResponse(HomeSystem homeSystem);
}
