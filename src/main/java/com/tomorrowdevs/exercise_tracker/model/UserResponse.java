package com.tomorrowdevs.exercise_tracker.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class UserResponse {

   private final String userName;
   private final String uuid;

    @JsonCreator
    public UserResponse(@JsonProperty("userName") String userName) {

        this.userName = userName;
        this.uuid = UUID.randomUUID().toString();
    }

    public String getUserName() {

        return userName;
    }

    public String getUuid() {

        return uuid;
    }

}
