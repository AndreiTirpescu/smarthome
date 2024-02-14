package com.mysmarthome.identityandaccess.infrastructure.repositories;

import com.mysmarthome.identityandaccess.domain.aggregate.User;
import com.mysmarthome.identityandaccess.domain.valueobjects.EmailAddress;
import com.mysmarthome.identityandaccess.domain.valueobjects.UserId;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataSqlRepository extends PagingAndSortingRepository<User, UserId> {
    Optional<User> findById(UserId id);
    Optional<User> findByEmail(EmailAddress email);
    boolean existsByEmail(EmailAddress email);
    User save(User user);
}
