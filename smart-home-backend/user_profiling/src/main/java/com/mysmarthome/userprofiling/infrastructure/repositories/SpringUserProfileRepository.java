package com.mysmarthome.userprofiling.infrastructure.repositories;

import com.mysmarthome.userprofiling.domain.aggregate.UserProfile;
import com.mysmarthome.userprofiling.domain.valueobjects.UserProfileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringUserProfileRepository extends JpaRepository<UserProfile, UserProfileId> {
    Optional<UserProfile> findByTagTag(String tag);
    Optional<UserProfile> findByIdentityIdentityId(String identityId);
}
