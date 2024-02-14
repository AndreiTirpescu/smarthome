package com.mysmarthome.identityandaccess.domain.infrastructure;

import com.mysmarthome.identityandaccess.domain.aggregate.User;
import com.mysmarthome.identityandaccess.domain.valueobjects.Token;
import com.mysmarthome.identityandaccess.domain.valueobjects.UserDescriptor;

public interface ITokenGenerator {
    Token generateTokenForUser(User user);

    UserDescriptor extractUserDescriptorFromToken(String accessToken);
}
