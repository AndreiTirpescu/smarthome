package com.mysmarthome.homesystemmanagement.domain.infrastructure;

import com.mysmarthome.homesystemmanagement.domain.aggregate.HomeSystem;
import com.mysmarthome.homesystemmanagement.domain.valueobjects.HomeSystemId;
import com.mysmarthome.homesystemmanagement.domain.valueobjects.Identity;

import java.util.Optional;

public interface IHomeSystemRepository {
    HomeSystemId nextIdentity();

    HomeSystem save(HomeSystem homeSystem);

    Optional<HomeSystem> findById(HomeSystemId homeSystemId);

    Optional<HomeSystem> findByIdentity(Identity identity);
}
