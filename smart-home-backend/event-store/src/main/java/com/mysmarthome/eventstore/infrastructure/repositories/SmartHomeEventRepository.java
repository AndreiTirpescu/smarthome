package com.mysmarthome.eventstore.infrastructure.repositories;

import com.mysmarthome.domain.PagedView;
import com.mysmarthome.eventstore.domain.SmartHomeEvent;
import com.mysmarthome.eventstore.domain.infrastructure.ISmartHomEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SmartHomeEventRepository implements ISmartHomEventRepository {

    private final SpringDataSmartHomeEventRepository springDataRepo;

    @Override
    public PagedView<SmartHomeEvent> allPagedByIdentity(String identity, int pageNumber, int pageSize) {
        var paged = springDataRepo.findAllByIdentity(
                identity,
                PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"))
        );

        return new PagedView<>(paged.stream().toList(), paged.getTotalElements(), paged.getTotalPages());
    }

    @Override
    public SmartHomeEvent save(SmartHomeEvent event) {
        return springDataRepo.save(event);
    }
}
