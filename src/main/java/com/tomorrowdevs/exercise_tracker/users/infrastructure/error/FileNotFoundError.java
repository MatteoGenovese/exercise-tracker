package com.tomorrowdevs.exercise_tracker.users.infrastructure.error;


public class FileNotFoundError extends RuntimeException {

    public FileNotFoundError(String message, Throwable e) {
        super(message+": %s".formatted(e));
    }
}
