package com.tomorrowdevs.exercise_tracker.users.infrastructure.controller.request;

import com.tomorrowdevs.exercise_tracker.users.domain.model.Username;
import jakarta.validation.constraints.Size;


public class UserRequest {

    private Username username;

    public UserRequest(Username username) {
        this.username = username;
    }

    public UserRequest() {
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
