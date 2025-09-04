package com.tomorrowdevs.exercise_tracker.model.api;

public class UserResponse {

   private String userName;
   private String uuid;

    public UserResponse() {
    }

    public UserResponse(String userName, String uuid) {

        this.userName = userName;
        this.uuid = uuid;
    }

    public String getUserName() {

        return userName;
    }

    public String getUuid() {

        return uuid;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public void setUuid(String uuid) {

        this.uuid = uuid;
    }

}
