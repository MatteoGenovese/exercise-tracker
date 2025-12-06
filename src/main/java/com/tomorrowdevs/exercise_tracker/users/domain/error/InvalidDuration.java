package com.tomorrowdevs.exercise_tracker.users.domain.error;

public class InvalidDuration extends RuntimeException {

    public InvalidDuration(String message) {
        super(message);
    }


    public static InvalidDuration isNegative() {
        return new InvalidDuration("Duration must be greater than 0");
    }

}
