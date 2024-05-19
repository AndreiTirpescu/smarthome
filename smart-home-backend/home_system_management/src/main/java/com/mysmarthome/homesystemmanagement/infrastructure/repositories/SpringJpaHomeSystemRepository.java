package com.mysmarthome.homesystemmanagement.infrastructure.repositories;

import com.mysmarthome.homesystemmanagement.domain.aggregate.HomeSystem;
import com.mysmarthome.homesystemmanagement.domain.valueobjects.HomeSystemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringJpaHomeSystemRepository extends JpaRepository<HomeSystem, HomeSystemId> {
    List<HomeSystem> findByIdentityIdentityId(String identityId);
}
