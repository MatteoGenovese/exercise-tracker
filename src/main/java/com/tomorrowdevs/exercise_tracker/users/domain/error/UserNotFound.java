package com.tomorrowdevs.exercise_tracker.users.domain.error;

public class UserNotFound extends InvalidData {

    public UserNotFound(String message) {
        super(message);
    }

    public static UserNotFound uuidNotFound() {
        return new UserNotFound("User not found");
    }

}
