package com.tomorrowdevs.exercise_tracker.exercises.infrastructure.repository.jpa;

import com.tomorrowdevs.exercise_tracker.users.infrastructure.repository.jpa.UserEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "exercises")
public class ExerciseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private LocalDateTime dateTime;

    private String description;

    private Integer duration;

    public ExerciseEntity() {
    }

    public ExerciseEntity(
            UserEntity user,
            LocalDateTime dateTime,
            String description,
            Integer duration
    ) {
        this.user = user;
        this.dateTime = dateTime;
        this.description = description;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

}
