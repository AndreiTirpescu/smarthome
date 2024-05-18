package com.mysmarthome.userprofiling.domain.valueobjects;

import com.mysmarthome.exceptions.SmartHomeException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import static com.mysmarthome.userprofiling.domain.exceptions.DomainExceptionCodes.InvalidTagException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@Getter
@Embeddable
public class ProfileTag implements Serializable {
    private String tag;

    public ProfileTag(String tag) {
        if (tag.length() != 10) {
            throw new SmartHomeException(InvalidTagException);
        }
        this.tag = tag;
    }

    @Override
    public String toString() {
        return tag;
    }
}
