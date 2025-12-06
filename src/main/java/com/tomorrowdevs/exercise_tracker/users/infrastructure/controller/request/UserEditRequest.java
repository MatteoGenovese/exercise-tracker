package com.tomorrowdevs.exercise_tracker.users.infrastructure.controller.request;

import com.tomorrowdevs.exercise_tracker.users.domain.model.Username;

import java.util.UUID;


public class UserEditRequest {

    private UUID uuid;
    private Username username;

    public UserEditRequest(
            UUID uuid,
            Username username
    ) {
        this.uuid = uuid;
        this.username = username;
    }

    public UserEditRequest() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public Username getUsername() {
        return username;
    }

}
