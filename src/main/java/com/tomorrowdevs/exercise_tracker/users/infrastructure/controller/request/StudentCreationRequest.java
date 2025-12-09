package com.tomorrowdevs.exercise_tracker.users.infrastructure.controller.request;

import com.tomorrowdevs.exercise_tracker.users.domain.model.Username;


public class StudentCreationRequest {

    private Username username;

    public StudentCreationRequest(Username username) {
        this.username = username;
    }

    public StudentCreationRequest() {
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
