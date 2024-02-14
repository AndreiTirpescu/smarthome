package com.mysmarthome.identityandaccess.domain.views;

import com.mysmarthome.identityandaccess.domain.aggregate.User;

import java.util.List;

public record PagedUserView(List<User> users, Long totalUsers, Integer totalPages) {
}
