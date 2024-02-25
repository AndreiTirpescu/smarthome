package com.mysmarthome.eventstore.application.mappers;

import com.mysmarthome.domain.PagedView;
import com.mysmarthome.eventstore.application.dtos.EventStoreResponse;
import com.mysmarthome.eventstore.application.dtos.PagedEventResponse;
import com.mysmarthome.eventstore.domain.SmartHomeEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EventStoreResponseMapper {
    EventStoreResponse responseFrom(SmartHomeEvent event);

    @Mappings(
            @Mapping(source = "data", target = "events")
    )
    PagedEventResponse responseFrom(PagedView<SmartHomeEvent> view);
}
