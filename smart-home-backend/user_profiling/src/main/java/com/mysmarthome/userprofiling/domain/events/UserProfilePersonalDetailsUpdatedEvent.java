package com.mysmarthome.userprofiling.domain.events;

import com.mysmarthome.domain.IDomainEvent;

public record UserProfilePersonalDetailsUpdatedEvent(String profileId, String identityId) implements IDomainEvent {
    @Override
    public String key() {
        return "user_profile_personal_details_updated";
    }
}
