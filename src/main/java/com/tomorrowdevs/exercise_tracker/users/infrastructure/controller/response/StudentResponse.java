package com.tomorrowdevs.exercise_tracker.users.infrastructure.controller.response;

import com.tomorrowdevs.exercise_tracker.users.domain.model.Username;

import java.util.Objects;


public record StudentResponse(String username, String uuid) {

    public static StudentResponse create(
            Username username,
            String uuid
    ) {
        return new StudentResponse(
                username.getValue(),
                uuid
        );
    }

    public boolean equals(StudentResponse user) {
        return Objects.equals(
                uuid,
                user.uuid
        );
    }

}
