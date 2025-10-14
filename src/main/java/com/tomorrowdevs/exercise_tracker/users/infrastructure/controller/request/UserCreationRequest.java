package com.tomorrowdevs.exercise_tracker.users.infrastructure.controller.request;

import com.tomorrowdevs.exercise_tracker.users.domain.model.Username;


public class UserCreationRequest {

    private Username username;

    public UserCreationRequest(Username username) {
        this.username = username;
    }

    public UserCreationRequest() {
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
