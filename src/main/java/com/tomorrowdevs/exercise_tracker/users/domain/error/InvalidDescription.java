package com.tomorrowdevs.exercise_tracker.users.domain.error;

public class InvalidDescription extends InvalidData {

    private InvalidDescription(String message) {
        super(message);
    }

    public static InvalidDescription tooShort() {
        return new InvalidDescription("Description too short");
    }

    public static InvalidDescription tooLong() {
        return new InvalidDescription("Description too long");
    }

}
