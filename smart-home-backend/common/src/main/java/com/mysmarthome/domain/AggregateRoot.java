package com.mysmarthome.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AggregateRoot {
    private final List<IDomainEvent> events;

    protected AggregateRoot() {
        this.events = new ArrayList<>();
    }

    protected void publishDomainEvent(IDomainEvent event) {
        events.add(event);
    }

    public void clearEvents() {
        events.clear();
    }

    public List<IDomainEvent> events() {
        return Collections.unmodifiableList(events);
    }
}
