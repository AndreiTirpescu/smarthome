package com.mysmarthome.userprofiling.domain.events;

import com.mysmarthome.domain.IDomainEvent;

public record UserProfileGeneratedForIdentityEvent(String profileId,
                                                   String identityId,
                                                   String emailAddress,
                                                   String tag) implements IDomainEvent {

    @Override
    public String key() {
        return "user_profile_generated_for_identity";
    }
}
