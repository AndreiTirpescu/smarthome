package com.mysmarthome.domain;


import com.google.gson.Gson;

public record Event(String id, String key, Long createdAt, String identity, String version, String application, String payload) {
    public static Event from(IDomainEvent evt) {
        return new Event(
                evt.id(), evt.key(), evt.createdAt(), null, evt.version(), null, new Gson().toJson(evt));
    }

    public Event withIdentity(String identity) {
        return new Event(id, key, createdAt, identity, version, application, payload);
    }

    public Event withApplication(String application) {
        return new Event(id, key, createdAt, identity, version, application, payload);
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static Event fromJson(String serialized) {
        return new Gson().fromJson(serialized, Event.class);
    }
}
