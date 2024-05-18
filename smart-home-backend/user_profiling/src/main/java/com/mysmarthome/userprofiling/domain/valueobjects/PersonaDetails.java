package com.mysmarthome.userprofiling.domain.valueobjects;

import com.github.javafaker.Faker;
import com.mysmarthome.exceptions.SmartHomeException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;

import static com.mysmarthome.userprofiling.domain.exceptions.DomainExceptionCodes.InvalidPersonaDetails;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@Getter
@Embeddable
public class PersonaDetails implements Serializable {
    private String firstName;

    private String lastName;

    private String middleName;

    public PersonaDetails(String firstName, String lastName, String middleName) {
        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
            throw new SmartHomeException(InvalidPersonaDetails);
        }
        this.firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
        this.lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
        this.middleName = middleName == null || middleName.isEmpty() ? null :
                middleName.substring(0, 1).toUpperCase() + middleName.substring(1).toLowerCase();
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public String middleName() {
        return middleName;
    }

    public ProfileTag generateTag() {
        return new ProfileTag(firstName.toLowerCase().substring(0, 3) + lastName.toLowerCase().substring(0, 4)
                + RandomStringUtils.randomNumeric(3));
    }

    public static PersonaDetails randomPersona() {
        var faker = new Faker();
        return new PersonaDetails(faker.animal().name(), faker.ancient().hero(), null);
    }
}
