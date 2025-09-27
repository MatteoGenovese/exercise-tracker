package com.tomorrowdevs.exercise_tracker.users.infrastructure.error;

public class DirectoryNotFoundError extends RuntimeException {

    public DirectoryNotFoundError(String message, Throwable e) {
        super(message+": %s".formatted(e));
    }
}
