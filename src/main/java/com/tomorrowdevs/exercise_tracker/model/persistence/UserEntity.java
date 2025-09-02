package com.tomorrowdevs.exercise_tracker.model.persistence;

public class UserEntity {

    private final String userName;
    private final String uuid;

    public UserEntity(String userName, String uuid) {

        this.userName = userName;
        this.uuid = uuid;
    }

    public String getUserName() {

        return userName;
    }

    public String getUuid() {

        return uuid;
    }

    @Override public String toString() {

        return "{" +
               "userName:'" + userName + "'," +
               "uuid:'" + uuid + "'" +
               '}';
    }

}
