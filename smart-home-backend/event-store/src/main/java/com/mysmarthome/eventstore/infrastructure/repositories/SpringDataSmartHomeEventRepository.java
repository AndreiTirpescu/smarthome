package com.mysmarthome.eventstore.infrastructure.repositories;

import com.mysmarthome.eventstore.domain.SmartHomeEvent;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataSmartHomeEventRepository extends PagingAndSortingRepository<SmartHomeEvent, UUID> {
    SmartHomeEvent save(SmartHomeEvent event);
}
