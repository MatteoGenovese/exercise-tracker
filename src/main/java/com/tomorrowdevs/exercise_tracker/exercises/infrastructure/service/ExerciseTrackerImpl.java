package com.tomorrowdevs.exercise_tracker.exercises.infrastructure.service;


import com.tomorrowdevs.exercise_tracker.exercises.application.repository.ExerciseRepository;
import com.tomorrowdevs.exercise_tracker.exercises.application.service.ExerciseTracker;
import com.tomorrowdevs.exercise_tracker.exercises.domain.model.Exercise;
import com.tomorrowdevs.exercise_tracker.users.application.repository.StudentRepository;
import com.tomorrowdevs.exercise_tracker.users.domain.error.StudentNotFound;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
public class ExerciseTrackerImpl implements ExerciseTracker {

    ExerciseRepository exerciseRepository;
    StudentRepository studentRepository;

    public ExerciseTrackerImpl(
            ExerciseRepository exerciseRepository,
            StudentRepository studentRepository
    ) {
        this.exerciseRepository = exerciseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public Exercise saveExerciseTrack(Exercise exerciseTrack) {

        LocalDateTime dateTime;
        Student student = studentRepository.findUserByUuid(exerciseTrack.studentUuid());

        if (student == null) {
            throw StudentNotFound.uuidNotFound();
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
                student.uuid()
        );
        exerciseRepository.save(exercise);

        return exercise;
    }

}
