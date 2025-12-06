package com.tomorrowdevs.exercise_tracker.exercises.application.repository;

import com.tomorrowdevs.exercise_tracker.exercises.domain.model.Exercise;

import java.util.List;
import java.util.UUID;


public interface ExerciseRepository {


    List<Exercise> read();

    void save(Exercise exerciseTrack);

    Exercise find(UUID uuid);

}
