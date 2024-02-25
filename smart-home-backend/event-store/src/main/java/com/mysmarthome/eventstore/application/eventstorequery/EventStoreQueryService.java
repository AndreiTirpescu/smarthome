package com.mysmarthome.eventstore.application.eventstorequery;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mysmarthome.eventstore.application.dtos.PagedEventResponse;
import com.mysmarthome.eventstore.application.mappers.EventStoreResponseMapper;
import com.mysmarthome.eventstore.domain.infrastructure.ISmartHomEventRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.mysmarthome.eventstore.application.config.SecurityUtils.identityFromContext;

@Service
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/events")
@Tag(name = "event-store")
public class EventStoreQueryService {

    private final ISmartHomEventRepository eventRepository;

    private final EventStoreResponseMapper mapper;

    @GetMapping
    @Operation(security = {@SecurityRequirement(name = "TokenAuthorization")})
    public PagedEventResponse getAllEvents(@RequestParam(defaultValue = "0") Integer pageNumber,
                                           @RequestParam(defaultValue = "100") Integer pageSize) {

        var events = eventRepository.allPagedByIdentity(identityFromContext(), pageNumber, pageSize);

        return mapper.responseFrom(events);
    }


}
