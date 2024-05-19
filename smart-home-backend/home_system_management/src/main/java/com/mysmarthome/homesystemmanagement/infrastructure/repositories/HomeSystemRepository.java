package com.mysmarthome.homesystemmanagement.infrastructure.repositories;

import com.mysmarthome.homesystemmanagement.domain.aggregate.HomeSystem;
import com.mysmarthome.homesystemmanagement.domain.infrastructure.IHomeSystemRepository;
import com.mysmarthome.homesystemmanagement.domain.valueobjects.HomeSystemId;
import com.mysmarthome.homesystemmanagement.domain.valueobjects.Identity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class HomeSystemRepository implements IHomeSystemRepository {

    private final SpringJpaHomeSystemRepository homeSystemJpaRepository;

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
}
