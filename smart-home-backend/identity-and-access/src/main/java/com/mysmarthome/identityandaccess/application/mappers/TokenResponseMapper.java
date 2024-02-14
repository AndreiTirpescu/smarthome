package com.mysmarthome.identityandaccess.application.mappers;

import com.mysmarthome.identityandaccess.application.dtos.TokenAuthorizationResponse;
import com.mysmarthome.identityandaccess.application.dtos.TokenResponse;
import com.mysmarthome.identityandaccess.domain.valueobjects.Token;
import com.mysmarthome.identityandaccess.domain.valueobjects.UserDescriptor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenResponseMapper {

    TokenResponse toResponse(Token token);

    TokenAuthorizationResponse responseFrom(UserDescriptor userDescriptor);
}
