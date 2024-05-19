package com.mysmarthome.homesystemmanagement.domain.infrastructure;


import com.mysmarthome.homesystemmanagement.domain.aggregate.HomeSystem;

public interface IDomainEventsPublisher {
    void publishDomainEventsFor(HomeSystem homeSystem);
}