package com.mysmarthome.userprofiling.infrastructure.application;

import com.mysmarthome.userprofiling.domain.aggregate.UserProfile;
import com.mysmarthome.userprofiling.domain.infrastructure.IDomainEventPublisher;
import com.mysmarthome.userprofiling.domain.infrastructure.IUserProfileRepository;
import com.mysmarthome.userprofiling.domain.valueobjects.Identity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class AutoCreateUserProfileHandler {

    private final IUserProfileRepository userProfileRepository;

    private final IDomainEventPublisher eventPublisher;

    @Transactional
    public void handle(AutoCreateUserProfile autoCreateUserProfile) {
        Identity identity = new Identity(autoCreateUserProfile.identityId(), autoCreateUserProfile.email());
        if (userProfileRepository.findByIdentityId(identity.id()).isPresent()) {
            log.error("User profile with identity id {} already exists", autoCreateUserProfile.identityId());
            return;
        }

        var profile = UserProfile.generateNewForIdentity(
                identity,
                userProfileRepository.nextIdentity()
        );

        log.info("Created profile profile: {}", profile);
        userProfileRepository.save(profile);

        eventPublisher.publishDomainEventsFor(profile);
    }

}
