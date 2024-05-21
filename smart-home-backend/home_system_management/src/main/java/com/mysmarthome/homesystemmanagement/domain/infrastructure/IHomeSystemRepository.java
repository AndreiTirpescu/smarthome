package com.mysmarthome.homesystemmanagement.domain.infrastructure;

import com.mysmarthome.domain.PagedView;
import com.mysmarthome.homesystemmanagement.domain.aggregate.HomeSystem;
import com.mysmarthome.homesystemmanagement.domain.model.Device;
import com.mysmarthome.homesystemmanagement.domain.valueobjects.HomeSystemId;
import com.mysmarthome.homesystemmanagement.domain.valueobjects.Identity;

import java.util.Optional;

public interface IHomeSystemRepository {
    HomeSystemId nextIdentity();

    HomeSystem save(HomeSystem homeSystem);

    Optional<HomeSystem> findById(HomeSystemId homeSystemId);

    Optional<HomeSystem> findByIdentity(Identity identity);

    PagedView<Device> devicesForHomeSystem(HomeSystem homeSystem, int pageNumber, int pageSize);
}
