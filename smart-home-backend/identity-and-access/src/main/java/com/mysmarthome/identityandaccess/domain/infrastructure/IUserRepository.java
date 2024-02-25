package com.mysmarthome.identityandaccess.domain.infrastructure;

import com.mysmarthome.domain.PagedView;
import com.mysmarthome.identityandaccess.domain.aggregate.User;
import com.mysmarthome.identityandaccess.domain.valueobjects.EmailAddress;
import com.mysmarthome.identityandaccess.domain.valueobjects.UserId;
import com.mysmarthome.identityandaccess.domain.views.PagedUserView;

import java.util.Optional;

public interface IUserRepository {
    UserId nextIdentity();

    boolean existsByEmail(EmailAddress emailAddress);

    Optional<User> findById(UserId id);

    Optional<User> findByEmail(EmailAddress emailAddress);

    User save(User user);

    PagedView<User> findAllPaged(int pageNumber, int pageSize);
}
