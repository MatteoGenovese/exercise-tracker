package com.tomorrowdevs.exercise_tracker.users.infrastructure.controller.request;

import jakarta.validation.constraints.Size;


public class ChangeUsernameRequest {

    @Size(
            min = 10,
            message = "Username must not be less then 10 characters"
    )
    private String username;

    public ChangeUsernameRequest(String username) {
        this.username = username;
    }

    public ChangeUsernameRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return username;
    }

}
