package com.mysmarthome.identityandaccess.domain.valueobjects;


import com.mysmarthome.exceptions.SmartHomeException;
import com.mysmarthome.identityandaccess.domain.aggregate.User;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;
import java.time.Instant;
import java.time.Period;

import static com.mysmarthome.identityandaccess.domain.exceptions.DomainExceptionCode.IamActivationTokenExpired;
import static com.mysmarthome.identityandaccess.domain.exceptions.DomainExceptionCode.IamInvalidActivationToken;

@Entity
@Table(name = "activation_tokens", schema = "identities")
@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ActivationToken implements Serializable {

    @EmbeddedId
    private UserId userId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private String value;

    private Long expiresAt;

    private ActivationToken(User user) {
        this.value = RandomStringUtils.randomAlphabetic(6).toUpperCase();
        this.expiresAt = Instant.now().plus(Period.ofDays(3)).toEpochMilli();
        this.user = user;
    }

    public static ActivationToken generateFor(User user) {
        return new ActivationToken(user);
    }

    public void activateUsing(String tokenValue) {
        if (!tokenValue.equals(value)) {
            throw new SmartHomeException(IamInvalidActivationToken);
        }

        if (Instant.now().toEpochMilli() > expiresAt) {
            throw new SmartHomeException(IamActivationTokenExpired);
        }

        expiresAt = Instant.now().toEpochMilli();
    }
}
