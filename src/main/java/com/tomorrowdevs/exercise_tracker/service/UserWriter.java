package com.tomorrowdevs.exercise_tracker.service;

import com.tomorrowdevs.exercise_tracker.model.api.UserRequest;
import com.tomorrowdevs.exercise_tracker.model.api.UserResponse;

public interface UserWriter {

    UserResponse save(UserRequest user);

}
