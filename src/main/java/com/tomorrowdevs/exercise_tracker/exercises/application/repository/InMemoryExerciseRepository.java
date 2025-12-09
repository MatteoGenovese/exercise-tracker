package com.tomorrowdevs.exercise_tracker.exercises.application.repository;

import com.tomorrowdevs.exercise_tracker.common.application.repository.InMemoryRepository;
import com.tomorrowdevs.exercise_tracker.exercises.domain.model.Exercise;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class InMemoryExerciseRepository extends InMemoryRepository<Exercise> implements ExerciseRepository {

}
