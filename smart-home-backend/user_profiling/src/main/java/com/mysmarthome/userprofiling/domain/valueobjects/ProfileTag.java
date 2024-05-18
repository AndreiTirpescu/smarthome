package com.mysmarthome.userprofiling.domain.valueobjects;

import com.mysmarthome.exceptions.SmartHomeException;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

import static com.mysmarthome.userprofiling.domain.exceptions.DomainExceptionCodes.InvalidTagException;

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
