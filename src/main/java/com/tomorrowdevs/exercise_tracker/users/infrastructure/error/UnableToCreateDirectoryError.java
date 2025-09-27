package com.tomorrowdevs.exercise_tracker.users.infrastructure.error;

public class UnableToCreateDirectoryError extends RuntimeException {

    public UnableToCreateDirectoryError(String message, Throwable e) {
        super(message+": %s".formatted(e));
    }
}
