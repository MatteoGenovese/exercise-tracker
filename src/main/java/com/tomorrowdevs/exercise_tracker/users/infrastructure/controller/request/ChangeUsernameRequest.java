package com.tomorrowdevs.exercise_tracker.users.infrastructure.controller.request;

import com.tomorrowdevs.exercise_tracker.users.domain.model.Username;
import jakarta.validation.constraints.Size;


public class ChangeUsernameRequest {

    private Username username;

    public ChangeUsernameRequest(Username username) {
        this.username = username;
    }

    public ChangeUsernameRequest() {
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

}
