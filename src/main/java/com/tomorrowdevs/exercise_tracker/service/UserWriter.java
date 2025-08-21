package com.tomorrowdevs.exercise_tracker.service;

import com.tomorrowdevs.exercise_tracker.model.UserRequest;
import com.tomorrowdevs.exercise_tracker.model.UserResponse;

import java.util.List;

public interface UserWriter {

    UserResponse save(UserRequest user);

}
