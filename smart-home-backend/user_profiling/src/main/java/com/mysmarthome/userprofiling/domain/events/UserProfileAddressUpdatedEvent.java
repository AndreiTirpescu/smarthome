package com.mysmarthome.userprofiling.domain.events;

import com.mysmarthome.domain.IDomainEvent;

public record UserProfileAddressUpdatedEvent(String profileId, String identityId) implements IDomainEvent {
    @Override
    public String key() {
        return "user_profile_address_updated";
    }
}
