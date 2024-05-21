package com.mysmarthome.homesystemmanagement.infrastructure.repositories;

import com.mysmarthome.domain.PagedView;
import com.mysmarthome.homesystemmanagement.domain.aggregate.HomeSystem;
import com.mysmarthome.homesystemmanagement.domain.infrastructure.IHomeSystemRepository;
import com.mysmarthome.homesystemmanagement.domain.model.Device;
import com.mysmarthome.homesystemmanagement.domain.valueobjects.HomeSystemId;
import com.mysmarthome.homesystemmanagement.domain.valueobjects.Identity;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class HomeSystemRepository implements IHomeSystemRepository {

    private final SpringJpaHomeSystemRepository homeSystemJpaRepository;
    private final SpringDeviceRepository deviceRepository;
    private final SpringDeviceRepository springDeviceRepository;

    @Override
    public HomeSystemId nextIdentity() {
        return new HomeSystemId(UUID.randomUUID().toString());
    }

    @Override
    public HomeSystem save(HomeSystem homeSystem) {
        return homeSystemJpaRepository.save(homeSystem);
    }

    @Override
    public Optional<HomeSystem> findById(HomeSystemId homeSystemId) {
        return homeSystemJpaRepository.findById(homeSystemId);
    }

    //@TODO return list when multiple homesystems per owner allowed
    @Override
    public Optional<HomeSystem> findByIdentity(Identity identity) {
        return homeSystemJpaRepository.findByIdentityIdentityId(identity.id()).stream().findFirst();
    }

    @Override
    public PagedView<Device> devicesForHomeSystem(HomeSystem homeSystem, int pageNumber, int pageSize) {
        Specification<Device> specification = Specification.where(null);
        specification = specification.and(HomeSystemDeviceSpecifications.byHomeSystem(homeSystem));
        var results = springDeviceRepository.findAll(specification, PageRequest.of(pageNumber, pageSize));

        return new PagedView<>(results.stream().toList(), results.getTotalElements(), results.getTotalPages());
    }

    public static class HomeSystemDeviceSpecifications {
        public static Specification<Device> byHomeSystem(HomeSystem homeSystem) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("homeSystem"), homeSystem);
        }
    }
}
