package com.mysmarthome.userprofiling.domain.infrastructure;

import com.mysmarthome.userprofiling.domain.aggregate.UserProfile;
import com.mysmarthome.userprofiling.domain.valueobjects.Identity;
import com.mysmarthome.userprofiling.domain.valueobjects.ProfileTag;
import com.mysmarthome.userprofiling.domain.valueobjects.UserProfileId;

import java.util.Optional;

public interface IUserProfileRepository {
    UserProfileId nextIdentity();

    Optional<UserProfile> findById(UserProfileId id);

    Optional<UserProfile> findByTag(ProfileTag tag);

    Optional<UserProfile> findByIdentityId(String identity);

    UserProfile save(UserProfile user);
}
