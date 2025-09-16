package com.tomorrowdevs.exercise_tracker.users.model.api;

import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class UserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String uuid;

    public UserJpaEntity() {
    }

    public UserJpaEntity(String userName, String uuid) {
        this.userName = userName;
        this.uuid = uuid;
    }

    public static UserJpaEntity create(String userName, String uuid) {
        return new UserJpaEntity(userName, uuid);
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
