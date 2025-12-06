package com.tomorrowdevs.exercise_tracker.exercises.infrastructure.service;


import com.tomorrowdevs.exercise_tracker.exercises.application.repository.ExerciseRepository;
import com.tomorrowdevs.exercise_tracker.exercises.application.service.ExerciseTracker;
import com.tomorrowdevs.exercise_tracker.exercises.domain.model.Exercise;
import com.tomorrowdevs.exercise_tracker.users.application.repository.UserRepository;
import com.tomorrowdevs.exercise_tracker.users.domain.error.UserNotFound;
import com.tomorrowdevs.exercise_tracker.users.domain.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
public class ExerciseTrackerImpl implements ExerciseTracker {

    ExerciseRepository exerciseRepository;
    UserRepository userRepository;

    public ExerciseTrackerImpl(
            ExerciseRepository exerciseRepository,
            UserRepository userRepository
    ) {
        this.exerciseRepository = exerciseRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Exercise saveExerciseTrack(Exercise exerciseTrack) {

        LocalDateTime dateTime;
        User user = userRepository.findUserByUuid(exerciseTrack.studentUuid());

        if (user == null) {
            throw UserNotFound.uuidNotFound();
        }

        if (exerciseTrack.dateTime() == null) {
            dateTime = LocalDateTime.now();
        } else {
            dateTime = exerciseTrack.dateTime();
        }

        Exercise exercise = new Exercise(
                UUID.randomUUID(),
                dateTime,
                exerciseTrack.description(),
                exerciseTrack.duration(),
                user.uuid()
        );
        exerciseRepository.save(exercise);

        return exercise;
    }

}
