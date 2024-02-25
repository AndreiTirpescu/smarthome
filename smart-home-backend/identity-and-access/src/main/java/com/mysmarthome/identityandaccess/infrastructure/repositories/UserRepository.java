package com.mysmarthome.identityandaccess.infrastructure.repositories;

import com.mysmarthome.domain.PagedView;
import com.mysmarthome.identityandaccess.domain.aggregate.User;
import com.mysmarthome.identityandaccess.domain.infrastructure.IUserRepository;
import com.mysmarthome.identityandaccess.domain.valueobjects.EmailAddress;
import com.mysmarthome.identityandaccess.domain.valueobjects.UserId;
import com.mysmarthome.identityandaccess.domain.views.PagedUserView;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class UserRepository implements IUserRepository {

    private final SpringDataSqlRepository springDataRepository;

    @Override
    public UserId nextIdentity() {
        return new UserId(UUID.randomUUID());
    }

    @Override
    public boolean existsByEmail(EmailAddress emailAddress) {
        return springDataRepository.existsByEmail(emailAddress);
    }

    @Override
    public Optional<User> findById(UserId id) {
        return springDataRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(EmailAddress emailAddress) {
        return springDataRepository.findByEmail(emailAddress);
    }

    @Override
    public User save(User user) {
        return springDataRepository.save(user);
    }

    @Override
    public PagedView<User> findAllPaged(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        var pagedUsers = springDataRepository.findAll(pageable);

        return new PagedView<>(pagedUsers.stream().toList(), pagedUsers.getTotalElements(), pagedUsers.getTotalPages());
    }
}
