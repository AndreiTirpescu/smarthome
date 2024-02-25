package com.mysmarthome.eventstore.domain.infrastructure;

import com.mysmarthome.domain.PagedView;
import com.mysmarthome.eventstore.domain.SmartHomeEvent;

public interface ISmartHomEventRepository {
    PagedView<SmartHomeEvent> allPagedByIdentity(String identity, int pageNumber, int pageSize);

    SmartHomeEvent save(SmartHomeEvent event);
}
