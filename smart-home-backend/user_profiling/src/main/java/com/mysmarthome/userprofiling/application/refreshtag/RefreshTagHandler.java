package com.mysmarthome.userprofiling.application.refreshtag;

import com.mysmarthome.exceptions.SmartHomeException;
import com.mysmarthome.userprofiling.application.dtos.UserProfileResponse;
import com.mysmarthome.userprofiling.application.mappers.UserProfileResponseMapper;
import com.mysmarthome.userprofiling.domain.infrastructure.IDomainEventPublisher;
import com.mysmarthome.userprofiling.domain.infrastructure.IUserProfileRepository;
import com.mysmarthome.userprofiling.domain.valueobjects.UserProfileId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mysmarthome.userprofiling.application.exceptions.ApplicationExceptionCodes.ProfileNotFoundById;

@Service
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/profiles")
@Tag(name= "profiles")
@RestController
public class RefreshTagHandler {

    private final IUserProfileRepository profileRepository;

    private final IDomainEventPublisher eventPublisher;

    private final UserProfileResponseMapper mapper;

    @PatchMapping("/{id}/tag-refresh")
    @Transactional
    @Operation(security = {@SecurityRequirement(name = "TokenAuthorization")})
    public UserProfileResponse refreshProfileTag(@PathVariable String id) {
        var profile = profileRepository.findById(new UserProfileId(id))
                .orElseThrow(() -> new SmartHomeException(ProfileNotFoundById));

        var profileTag = profile.suggestTag();
        profile.updateTag(profileTag.toString());

        eventPublisher.publishDomainEventsFor(profile);

        return mapper.toUserProfileResponse(profile);
    }
}
