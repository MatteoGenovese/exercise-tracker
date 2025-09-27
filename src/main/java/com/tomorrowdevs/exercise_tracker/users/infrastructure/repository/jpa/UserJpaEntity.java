package com.tomorrowdevs.exercise_tracker.users.infrastructure.repository.jpa;

import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class UserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String uuid;

    public UserJpaEntity() {
    }

    public UserJpaEntity(String username, String uuid) {
        this.username = username;
        this.uuid = uuid;
    }

    public static UserJpaEntity create(String username, String uuid) {
        return new UserJpaEntity(username, uuid);
    }

    public String getUsername() {
        return username;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
