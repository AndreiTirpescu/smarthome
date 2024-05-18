package com.mysmarthome.userprofiling.application.updateprofileaddress;

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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mysmarthome.userprofiling.application.exceptions.ApplicationExceptionCodes.ProfileNotFoundById;

@Service
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/profiles")
@Tag(name= "profiles")
@RestController
public class UpdateProfileAddressHandler {
    private final IUserProfileRepository profileRepository;

    private final IDomainEventPublisher eventPublisher;

    private final UserProfileResponseMapper mapper;

    @PatchMapping("/{id}/address")
    @Transactional
    @Operation(security = {@SecurityRequirement(name = "TokenAuthorization")})
    public UserProfileResponse updateProfileAddress(@Valid @RequestBody UpdateProfileAddress request, @PathVariable String id) {
        var profile = profileRepository.findById(new UserProfileId(id))
                .orElseThrow(() -> new SmartHomeException(ProfileNotFoundById));

        profile.updateAddress(request.countryCode(), request.addressLine1(), request.addressLine2(),
                request.city(), request.county(), request.postalCode());

        eventPublisher.publishDomainEventsFor(profile);

        return mapper.toUserProfileResponse(profile);
    }
}
