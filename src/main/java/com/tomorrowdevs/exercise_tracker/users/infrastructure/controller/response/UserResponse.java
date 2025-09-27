package com.tomorrowdevs.exercise_tracker.users.infrastructure.controller.response;

import com.tomorrowdevs.exercise_tracker.users.domain.model.Username;

import java.util.Objects;


public record UserResponse( String username, String uuid ) {

    public static UserResponse create(Username username, String uuid) {
        return new UserResponse(username.getValue(), uuid);
    }

    public boolean equals(UserResponse user) {
        return Objects.equals(uuid, user.uuid);
    }

}
