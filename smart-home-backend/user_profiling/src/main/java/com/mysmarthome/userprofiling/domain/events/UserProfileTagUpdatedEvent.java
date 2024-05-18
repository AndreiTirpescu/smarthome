package com.mysmarthome.userprofiling.domain.events;

import com.mysmarthome.domain.IDomainEvent;

public record UserProfileTagUpdatedEvent(String profileId, String identityId, String tag) implements IDomainEvent {
    @Override
    public String key() {
        return "user_profile_tag_updated";
    }
}
