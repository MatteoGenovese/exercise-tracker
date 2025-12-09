package com.tomorrowdevs.exercise_tracker.users.domain.model;

import com.tomorrowdevs.exercise_tracker.common.domain.entity.Entity;

import java.util.Objects;
import java.util.UUID;


public record Student(UUID uuid, Username username) implements Entity {

    public static Student create(String username) {
        return new Student(UUID.randomUUID(), new Username(username));
    }

    public static Student create(UUID uuid, Username username) {
        return new Student(uuid, username);
    }

    public static Student create(UUID uuid, String username) {
        return new Student(uuid, new Username(username));
    }

    public boolean equals(Student student) {
        return Objects.equals(uuid, student.uuid);
    }

}