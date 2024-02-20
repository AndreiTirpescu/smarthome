package com.mysmarthome.domain;

import java.time.Instant;
import java.util.UUID;

public interface IDomainEvent {

    default String id () {
        return UUID.randomUUID().toString();
    }

    default Long createdAt() {
        return Instant.now().toEpochMilli();
    }

    default String version() {
        return "v1";
    }

    String key();
}
