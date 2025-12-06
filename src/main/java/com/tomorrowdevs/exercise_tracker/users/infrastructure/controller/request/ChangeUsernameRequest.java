package com.tomorrowdevs.exercise_tracker.users.infrastructure.controller.request;

import com.tomorrowdevs.exercise_tracker.users.domain.model.Username;

import java.util.UUID;


public class ChangeUsernameRequest {

    private UUID uuid;
    private Username username;

    public ChangeUsernameRequest(
            UUID uuid,
            Username username
    ) {
        this.uuid = uuid;
        this.username = username;
    }

    public ChangeUsernameRequest() {
    }

    public static ChangeUsernameRequest create(
            UUID uuid,
            Username username
    ) {
        return new ChangeUsernameRequest(
                uuid,
                username
        );
    }

    public Username getUsername() {
        return username;
    }

    public void setUsername(Username username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return username.getValue();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

}
