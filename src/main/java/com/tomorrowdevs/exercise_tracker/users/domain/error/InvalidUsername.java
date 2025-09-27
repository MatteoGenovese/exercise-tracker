package com.tomorrowdevs.exercise_tracker.users.domain.error;

public class InvalidUsername extends InvalidData {
    private InvalidUsername(String message) {
        super(message);
    }

    public static InvalidUsername tooShort() {
        return new InvalidUsername("Username too short");
    }

    public static InvalidUsername tooLong() {
        return new InvalidUsername("Username too long");
    }
}
