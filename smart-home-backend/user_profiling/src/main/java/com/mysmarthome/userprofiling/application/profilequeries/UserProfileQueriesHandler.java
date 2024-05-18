package com.mysmarthome.userprofiling.application.profilequeries;

import com.mysmarthome.exceptions.SmartHomeException;
import com.mysmarthome.userprofiling.application.dtos.UserProfileResponse;
import com.mysmarthome.userprofiling.application.mappers.UserProfileResponseMapper;
import com.mysmarthome.userprofiling.domain.infrastructure.IUserProfileRepository;
import com.mysmarthome.userprofiling.domain.valueobjects.Identity;
import com.mysmarthome.userprofiling.domain.valueobjects.ProfileTag;
import com.mysmarthome.userprofiling.domain.valueobjects.UserProfileId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mysmarthome.userprofiling.application.exceptions.ApplicationExceptionCodes.ProfileNotFoundById;
import static com.mysmarthome.userprofiling.application.exceptions.ApplicationExceptionCodes.ProfileNotFoundByTag;

@Service
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/profiles")
@Tag(name= "profiles")
public class UserProfileQueriesHandler {

    private final IUserProfileRepository userProfileRepository;
    private final UserProfileResponseMapper responseMapper;

    @GetMapping("/{id}")
    @Operation(security = {@SecurityRequirement(name = "TokenAuthorization")})
    public UserProfileResponse findProfileById(@PathVariable String id) {
        var profile = userProfileRepository.findById(new UserProfileId(id))
                .orElseThrow(() -> new SmartHomeException(ProfileNotFoundById));

        return responseMapper.toUserProfileResponse(profile);
    }

    @GetMapping("tag/{tag}")
    @Operation(security = {@SecurityRequirement(name = "TokenAuthorization")})
    public UserProfileResponse findByTag(@PathVariable String tag) {
        var profile = userProfileRepository.findByTag(new ProfileTag(tag))
                .orElseThrow(() -> new SmartHomeException(ProfileNotFoundByTag));

        return responseMapper.toUserProfileResponse(profile);
    }

    @GetMapping("identity/{identityId}")
    @Operation(security = {@SecurityRequirement(name = "TokenAuthorization")})
    public UserProfileResponse findByIdentity(@PathVariable String identityId) {
        var profile = userProfileRepository.findByIdentityId(identityId)
                .orElseThrow(() -> new SmartHomeException(ProfileNotFoundByTag));

        return responseMapper.toUserProfileResponse(profile);
    }

}
