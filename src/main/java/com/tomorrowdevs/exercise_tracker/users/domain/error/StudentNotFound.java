package com.tomorrowdevs.exercise_tracker.users.domain.error;

import com.tomorrowdevs.exercise_tracker.common.domain.EntityNotFound;

public class StudentNotFound  extends InvalidData {

    public StudentNotFound(String message) {
        super(message);
    }

    public static StudentNotFound uuidNotFound() {
        return new StudentNotFound("Student not found");
    }

}
