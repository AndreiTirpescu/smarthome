package com.mysmarthome.domain;

import java.time.Instant;

public interface IDomainEvent {

    default Long createdAt() {
        return Instant.now().toEpochMilli();
    }

    default String version() {
        return "v1";
    }
}
