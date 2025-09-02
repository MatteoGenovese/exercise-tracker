package com.tomorrowdevs.exercise_tracker.model.domain;

import java.util.UUID;

public class UserDomain {


    private final String userName;
    private final String uuid;


    public UserDomain(String userName) {

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
