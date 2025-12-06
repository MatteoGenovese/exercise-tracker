package com.tomorrowdevs.exercise_tracker.exercises.application.repository;

import com.tomorrowdevs.exercise_tracker.exercises.domain.model.Exercise;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class InMemoryExerciseRepository implements ExerciseRepository {

    Map<UUID, Exercise> exerciseMap = new HashMap<>();

    @Override
    public List<Exercise> read() {
        return new ArrayList<>(exerciseMap.values());
    }

    @Override
    public void save(Exercise exerciseTrack) {
        exerciseMap.put(
                exerciseTrack.uuid(),
                exerciseTrack
        );
    }

    @Override
    public Exercise find(UUID uuid) {
        return exerciseMap.get(uuid);
    }

}
