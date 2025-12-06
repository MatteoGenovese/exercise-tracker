package com.tomorrowdevs.exercise_tracker.exercises.domain.model;

import com.tomorrowdevs.exercise_tracker.users.domain.error.InvalidDuration;


public class TimeDuration {

    Integer value;

    public TimeDuration(Integer value) {
        this.value = value;
    }

    public static TimeDuration create(Integer value) {
        if (value <= 0) {
            throw InvalidDuration.isNegative();
        }
        return new TimeDuration(value);
    }

    public static TimeDuration create(String value) {
        if (Integer.parseInt(value) <= 0) {
            throw InvalidDuration.isNegative();
        }
        return new TimeDuration(Integer.parseInt(value));
    }

    public Integer getValue() {
        return value;
    }

}
