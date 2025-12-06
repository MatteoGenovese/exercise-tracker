package com.tomorrowdevs.exercise_tracker.users.domain.error;

public class UserEmptyList extends RuntimeException {

    public UserEmptyList(String message) {
        super(message);
    }

}
