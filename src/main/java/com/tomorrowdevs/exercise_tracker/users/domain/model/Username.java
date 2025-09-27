package com.tomorrowdevs.exercise_tracker.users.domain.model;

import com.tomorrowdevs.exercise_tracker.users.domain.error.InvalidUsername;

import java.util.Objects;


public class Username {
    private String value;

    public Username(String value) {
        this.value = assertUsernameIsValid(value);
    }

    private String assertUsernameIsValid(String value) {
        String trimmedValue = value.trim();

        if (trimmedValue.length() < 8) throw InvalidUsername.tooShort();
        if (trimmedValue.length() > 25) throw InvalidUsername.tooLong();

        return trimmedValue;
    }

    public String getValue() {
        return value;
    }

    @Override public String toString() {
        return getValue();
    }

    public boolean equalsTo(Username obj) {
        return Objects.equals(obj.getValue(), this.value);
    }
}
