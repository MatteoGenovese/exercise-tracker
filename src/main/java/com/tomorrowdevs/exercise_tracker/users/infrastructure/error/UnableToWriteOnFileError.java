package com.tomorrowdevs.exercise_tracker.users.infrastructure.error;

public class UnableToWriteOnFileError extends RuntimeException {

    public UnableToWriteOnFileError(String message, Throwable e) {
        super(message+": %s".formatted(e));
    }
}
