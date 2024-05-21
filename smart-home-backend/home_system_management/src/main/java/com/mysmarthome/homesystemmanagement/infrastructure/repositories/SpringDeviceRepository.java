package com.mysmarthome.homesystemmanagement.infrastructure.repositories;

import com.mysmarthome.homesystemmanagement.domain.model.Device;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SpringDeviceRepository extends PagingAndSortingRepository<Device, Long> {

    Page<Device> findAll(Specification<Device> specification, @NotNull Pageable pageable);
}
