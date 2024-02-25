package com.mysmarthome.eventstore.application.dtos;

public record EventStoreResponse(String key, Long createdAt, String version, String payload) {
}
