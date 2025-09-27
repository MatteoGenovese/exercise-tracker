package com.tomorrowdevs.exercise_tracker.users.domain.error;

public abstract class InvalidData extends DomainError {

    public InvalidData(String message) {
        super(message);
    }
}
