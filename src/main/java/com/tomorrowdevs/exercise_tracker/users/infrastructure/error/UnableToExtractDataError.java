package com.tomorrowdevs.exercise_tracker.users.infrastructure.error;

public class UnableToExtractDataError extends RuntimeException {

    public UnableToExtractDataError(String message, Throwable e) {
        super(message+": %s".formatted(e));
    }
}
