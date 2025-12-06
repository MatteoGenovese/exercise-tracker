package com.tomorrowdevs.exercise_tracker.exercises.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


public record Exercise(UUID uuid, LocalDateTime dateTime, ExerciseDescription description, TimeDuration duration, UUID studentUuid) {

    public static Exercise create(
            LocalDateTime date,
            ExerciseDescription description,
            TimeDuration duration,
            UUID studentUuid
    ) {
        return validate(UUID.randomUUID(),date,description,duration,studentUuid);
    }


    public static Exercise create(
            UUID uuid,
            LocalDateTime date,
            ExerciseDescription description,
            TimeDuration duration,
            UUID studentUuid
    ) {
        return validate(uuid,date,description,duration,studentUuid);
    }

    private static Exercise validate(
            UUID uuid,
            LocalDateTime date,
            ExerciseDescription description,
            TimeDuration duration,
            UUID studentUuid
    ){

        if (date == null) {
            date = LocalDateTime.now();
        }

        return new Exercise(
                uuid,
                date,
                description,
                duration,
                studentUuid
        );
    }


    public boolean equals(Exercise exercise) {
        return Objects.equals(uuid, exercise.uuid);
    }

}
