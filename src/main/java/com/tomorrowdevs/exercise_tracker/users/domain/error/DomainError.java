package com.tomorrowdevs.exercise_tracker.users.domain.error;

public abstract class DomainError extends RuntimeException {
    public DomainError(String message) {
        super(message);
    }
}
