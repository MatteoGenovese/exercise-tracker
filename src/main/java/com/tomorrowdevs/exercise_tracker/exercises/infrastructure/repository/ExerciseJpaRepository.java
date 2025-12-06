package com.tomorrowdevs.exercise_tracker.exercises.infrastructure.repository;

import com.tomorrowdevs.exercise_tracker.exercises.infrastructure.repository.jpa.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExerciseJpaRepository extends JpaRepository<ExerciseEntity, Long> {

}
