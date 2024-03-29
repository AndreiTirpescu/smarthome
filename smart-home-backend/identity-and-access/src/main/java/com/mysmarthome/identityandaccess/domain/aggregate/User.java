package com.mysmarthome.identityandaccess.domain.aggregate;

import com.mysmarthome.domain.AggregateRoot;
import com.mysmarthome.exceptions.SmartHomeException;
import com.mysmarthome.identityandaccess.domain.events.UserActivatedEvent;
import com.mysmarthome.identityandaccess.domain.events.UserCreatedEvent;
import com.mysmarthome.identityandaccess.domain.valueobjects.ActivationToken;
import com.mysmarthome.identityandaccess.domain.valueobjects.EmailAddress;
import com.mysmarthome.identityandaccess.domain.valueobjects.EncodedPassword;
import com.mysmarthome.identityandaccess.domain.valueobjects.UserId;
import com.mysmarthome.identityandaccess.domain.valueobjects.UserRole;
import com.mysmarthome.identityandaccess.domain.valueobjects.UserStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

import static com.mysmarthome.identityandaccess.domain.exceptions.DomainExceptionCode.IamInactiveUserCannotActivate;
import static com.mysmarthome.identityandaccess.domain.exceptions.DomainExceptionCode.IamUserAlreadyActive;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@Getter
@Entity
@Table(name = "users", schema = "identities")
public class User extends AggregateRoot {

    @EmbeddedId
    private UserId id;

    @Embedded
    private EmailAddress email;

    @Embedded
    private EncodedPassword password;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "identities.user_role")
    private UserRole role;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "identities.user_status")
    private UserStatus status;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ActivationToken token;

    private User(UserId id, EmailAddress email, EncodedPassword password, UserRole role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = UserStatus.PENDING_ACTIVATION;
        token = ActivationToken.generateFor(this);

        publishDomainEvent(new UserCreatedEvent(id.toString(), email.toString()));
    }

    public static User standardUser(UserId id, EmailAddress address, EncodedPassword encodedPassword) {
        return new User(id, address, encodedPassword, UserRole.STANDARD);
    }

    public void activateAccountUsing(String tokenValue) {
        if (UserStatus.INACTIVE == status) {
            throw new SmartHomeException(IamInactiveUserCannotActivate);
        }

        if (UserStatus.ACTIVE == status) {
            throw new SmartHomeException(IamUserAlreadyActive);
        }

        token.activateUsing(tokenValue);
        status = UserStatus.ACTIVE;

        publishDomainEvent(new UserActivatedEvent(id.toString(), Instant.now().toEpochMilli()));
    }
}
