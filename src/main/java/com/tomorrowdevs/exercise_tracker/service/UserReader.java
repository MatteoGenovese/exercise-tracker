package com.tomorrowdevs.exercise_tracker.service;

import com.tomorrowdevs.exercise_tracker.model.api.UserResponse;

import java.util.List;

public interface UserReader {

   List<UserResponse> read();

}
