package com.mysmarthome.identityandaccess.application.dtos;

import java.util.List;

public record PagedUserResponse(List<UserResponse> users, Long count, Long totalPages) { }
