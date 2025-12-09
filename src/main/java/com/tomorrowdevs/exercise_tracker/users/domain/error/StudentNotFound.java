package com.tomorrowdevs.exercise_tracker.users.domain.error;

public class StudentNotFound extends InvalidData {

    public StudentNotFound(String message) {
        super(message);
    }

    public static StudentNotFound uuidNotFound() {
        return new StudentNotFound("User not found");
    }

}
