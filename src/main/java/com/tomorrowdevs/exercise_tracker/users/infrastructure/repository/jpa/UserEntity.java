package com.tomorrowdevs.exercise_tracker.users.infrastructure.repository.jpa;

import com.tomorrowdevs.exercise_tracker.exercises.infrastructure.repository.jpa.ExerciseEntity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "users")
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String username;

    @OneToMany(mappedBy = "user")
    private Set<ExerciseEntity> exercises = new HashSet<>();

    public UserEntity() {
    }

    public UserEntity(String username, UUID uuid) {
        this.username = username;
        this.uuid = uuid;
    }

    public static UserEntity create(String username, UUID uuid) {
        return new UserEntity(username, uuid);
    }


    public Set<ExerciseEntity> getExercises() {
        return exercises;
    }

    public void setExercises(Set<ExerciseEntity> exercises) {
        this.exercises = exercises;
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
