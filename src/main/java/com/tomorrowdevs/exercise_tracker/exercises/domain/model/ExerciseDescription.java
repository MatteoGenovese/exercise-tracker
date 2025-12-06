package com.tomorrowdevs.exercise_tracker.exercises.domain.model;

import com.tomorrowdevs.exercise_tracker.users.domain.error.InvalidDescription;

import java.util.Objects;


public class ExerciseDescription {

    String value;

    public ExerciseDescription(String value) {
        this.value = value;
    }


    public static ExerciseDescription create(String value) {
        if (value.length() < 4) {
            throw InvalidDescription.tooShort();
        }
        if (value.length() > 200) {
            throw InvalidDescription.tooLong();
        }
        return new ExerciseDescription(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExerciseDescription that = (ExerciseDescription) o;
        return Objects.equals(
                value,
                that.value
        );
    }

}
