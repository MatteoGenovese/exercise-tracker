package com.tomorrowdevs.exercise_tracker.users.model.domain;

import java.util.Objects;
import java.util.UUID;


public record User( String username, UUID uuid ) {

    public static User create(String username) {
        return new User(username, UUID.randomUUID());
    }

    public static User create(String username, UUID uuid) {
        return new User(username, uuid);
    }

    public boolean equals(User user) {
        return Objects.equals(uuid, user.uuid);
    }

}
