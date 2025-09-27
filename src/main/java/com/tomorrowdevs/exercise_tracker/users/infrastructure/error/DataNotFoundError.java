package com.tomorrowdevs.exercise_tracker.users.infrastructure.error;

public class DataNotFoundError extends RuntimeException {

    public DataNotFoundError(String message, Throwable e) {
        super(message+": %s".formatted(e));
    }

}