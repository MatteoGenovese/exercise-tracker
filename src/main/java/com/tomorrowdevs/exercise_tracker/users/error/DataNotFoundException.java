package com.tomorrowdevs.exercise_tracker.users.error;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message) {
        super(message);
    }
}
