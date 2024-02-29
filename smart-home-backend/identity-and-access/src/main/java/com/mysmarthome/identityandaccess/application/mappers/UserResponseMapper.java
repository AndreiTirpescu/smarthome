package com.mysmarthome.identityandaccess.application.mappers;

import com.mysmarthome.domain.PagedView;
import com.mysmarthome.identityandaccess.application.dtos.PagedUserResponse;
import com.mysmarthome.identityandaccess.application.dtos.UserResponse;
import com.mysmarthome.identityandaccess.domain.aggregate.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {
    @Mappings({
            @Mapping(expression = "java(user.getId().toString())", target = "id"),
            @Mapping(expression = "java(user.getEmail().toString())", target = "email"),
            @Mapping(expression = "java(user.getRole().toString())", target = "role"),
            @Mapping(expression = "java(user.getStatus().toString())", target = "status"),
    })
    UserResponse responseFrom(User user);


    @Mappings(
            @Mapping(source = "data", target = "users")
    )
    PagedUserResponse pagedResponseFrom(PagedView<User> view);
}
