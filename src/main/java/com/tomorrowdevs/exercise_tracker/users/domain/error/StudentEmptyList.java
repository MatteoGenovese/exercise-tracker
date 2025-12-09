package com.tomorrowdevs.exercise_tracker.users.domain.error;

public class StudentEmptyList extends RuntimeException {

    public StudentEmptyList(String message) {
        super(message);
    }

}
