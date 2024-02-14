package com.mysmarthome.identityandaccess.application.userquery;

import com.mysmarthome.exceptions.SmartHomeException;
import com.mysmarthome.identityandaccess.application.dtos.PagedUserResponse;
import com.mysmarthome.identityandaccess.application.dtos.UserResponse;
import com.mysmarthome.identityandaccess.application.exceptions.ApplicationExceptionCode;
import com.mysmarthome.identityandaccess.application.mappers.UserResponseMapper;
import com.mysmarthome.identityandaccess.domain.infrastructure.IUserRepository;
import com.mysmarthome.identityandaccess.domain.valueobjects.UserId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/users")
@Tag(name = "users")
public class UserQueryHandler {

    private final IUserRepository repository;

    private final UserResponseMapper mapper;

    @GetMapping("/{id}")
    @Operation(security = {@SecurityRequirement(name = "TokenAuthorization")})
    public UserResponse findUserById(@NotEmpty @PathVariable String id) {

        return repository
                .findById(new UserId(id)).map(mapper::responseFrom)
                .orElseThrow(() -> new SmartHomeException(ApplicationExceptionCode.IamUserNotFoundById));
    }

    @GetMapping
    @Operation(security = {@SecurityRequirement(name = "TokenAuthorization")})
    public PagedUserResponse findAllUsersPaged(@RequestParam(defaultValue = "0") Integer pageNumber,
                                               @RequestParam(defaultValue = "100") Integer pageSize) {

        var usersPaged = repository.findAllPaged(pageNumber, pageSize);

        return mapper.pagedResponseFrom(usersPaged);
    }
}
