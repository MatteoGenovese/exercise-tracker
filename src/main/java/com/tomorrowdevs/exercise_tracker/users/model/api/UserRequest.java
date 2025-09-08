package com.tomorrowdevs.exercise_tracker.users.model.api;

import jakarta.validation.constraints.Size;

public class UserRequest {

    @Size(min = 8, message = "Username must not be less then 8 characters")
    private String userName;

    public UserRequest(String userName) {
        this.userName = userName;
    }

    public UserRequest() {

    }

    public String getUsername() {

        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    @Override
    public String toString() {
        return userName;
    }

}
