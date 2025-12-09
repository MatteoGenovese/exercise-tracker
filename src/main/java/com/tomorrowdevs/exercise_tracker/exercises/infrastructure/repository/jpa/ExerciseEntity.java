package com.tomorrowdevs.exercise_tracker.exercises.infrastructure.repository.jpa;

import com.tomorrowdevs.exercise_tracker.users.infrastructure.repository.jpa.StudentEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "exercises")
public class ExerciseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    private LocalDateTime dateTime;

    private String description;

    private Integer duration;

    public ExerciseEntity() {
    }

    public ExerciseEntity(
            StudentEntity student,
            LocalDateTime dateTime,
            String description,
            Integer duration
    ) {
        this.student = student;
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

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
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
