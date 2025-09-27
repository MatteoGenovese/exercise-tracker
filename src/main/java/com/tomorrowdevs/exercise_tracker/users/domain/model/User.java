package com.tomorrowdevs.exercise_tracker.users.domain.model;

import java.util.Objects;
import java.util.UUID;


public record User( UUID uuid, Username username) {

    public static User create(String username) {
        return new User(UUID.randomUUID(), new Username(username));
    }

    public static User create( UUID uuid, String username) {
        return new User(uuid, new Username(username));
    }

    public boolean equals(User user) {
        return Objects.equals(uuid, user.uuid);
    }

}