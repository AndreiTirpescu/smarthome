package com.mysmarthome.eventstore.domain.infrastructure;

import com.mysmarthome.domain.PagedView;
import com.mysmarthome.eventstore.domain.SmartHomeEvent;

import java.util.Optional;

public interface ISmartHomEventRepository {
    PagedView<SmartHomeEvent> allPaged(int pageNumber, int pageSize);

    SmartHomeEvent save(SmartHomeEvent event);
}
