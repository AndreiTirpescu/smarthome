package com.mysmarthome.userprofiling.domain.aggregate;

import com.mysmarthome.domain.AggregateRoot;
import com.mysmarthome.userprofiling.domain.events.UserProfileGeneratedForIdentityEvent;
import com.mysmarthome.userprofiling.domain.valueobjects.Address;
import com.mysmarthome.userprofiling.domain.valueobjects.Identity;
import com.mysmarthome.userprofiling.domain.valueobjects.PersonaDetails;
import com.mysmarthome.userprofiling.domain.valueobjects.ProfileTag;
import com.mysmarthome.userprofiling.domain.valueobjects.UserProfileId;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@Getter
@Entity
@Table(name = "user_profiles", schema = "profiling")
public class UserProfile extends AggregateRoot {
    @EmbeddedId
    private UserProfileId id;

    @Embedded
    private Identity identity;

    @Embedded
    private PersonaDetails personalDetails;

    @Embedded
    private Address address;

    @Embedded
    private ProfileTag tag;

    private String profileImageUrl;

    private UserProfile(UserProfileId profileId, Identity identity, PersonaDetails personalDetails, Address address) {
        this.id = profileId;
        this.identity = identity;
        this.personalDetails = personalDetails;
        this.address = address;
        this.profileImageUrl = null;
        this.tag = personalDetails.generateTag();

        publishDomainEvent(
                new UserProfileGeneratedForIdentityEvent(id.toString(), identity.id(), identity.email(), tag.toString())
        );
    }

    public static UserProfile generateNewForIdentity(Identity identity, UserProfileId profileId) {
        return new UserProfile(profileId, identity, PersonaDetails.randomPersona(), Address.emptyAddress());
    }

    public void updateProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void updateTag(String tag) {
        this.tag = new ProfileTag(tag);
    }

    public void updatePersonalDetails(String firstName, String lastName, String middleName) {
        this.personalDetails = new PersonaDetails(firstName, lastName, middleName);
    }

    public void updateAddress(String countryCode, String line1, String line2, String city, String county, String postalCode) {
        this.address = new Address(countryCode, line1, line2, city, county, postalCode);
    }
}
