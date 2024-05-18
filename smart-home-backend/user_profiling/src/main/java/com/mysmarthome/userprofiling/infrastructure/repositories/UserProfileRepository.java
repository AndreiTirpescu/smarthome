package com.mysmarthome.userprofiling.infrastructure.repositories;

import com.mysmarthome.userprofiling.domain.aggregate.UserProfile;
import com.mysmarthome.userprofiling.domain.infrastructure.IUserProfileRepository;
import com.mysmarthome.userprofiling.domain.valueobjects.Identity;
import com.mysmarthome.userprofiling.domain.valueobjects.ProfileTag;
import com.mysmarthome.userprofiling.domain.valueobjects.UserProfileId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserProfileRepository implements IUserProfileRepository {

    private final SpringUserProfileRepository springUserProfileRepository;

    @Override
    public UserProfileId nextIdentity() {
        return new UserProfileId(UUID.randomUUID().toString());
    }

    @Override
    public Optional<UserProfile> findById(UserProfileId id) {
        return springUserProfileRepository.findById(id);
    }

    @Override
    public Optional<UserProfile> findByTag(ProfileTag tag) {
        return springUserProfileRepository.findByTagTag(tag.toString());
    }

    @Override
    public Optional<UserProfile> findByIdentityId(String identity) {
        return springUserProfileRepository.findByIdentityIdentityId(identity);
    }

    @Override
    public UserProfile save(UserProfile user) {
        return springUserProfileRepository.save(user);
    }
}
