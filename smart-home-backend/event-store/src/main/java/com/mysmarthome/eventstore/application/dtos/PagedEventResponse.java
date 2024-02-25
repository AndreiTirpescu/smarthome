package com.mysmarthome.eventstore.application.dtos;

import java.util.List;

public record PagedEventResponse(List<EventStoreResponse> events, Long count, Integer totalPages) {
}
