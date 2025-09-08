package com.tomorrowdevs.exercise_tracker.users.service;

import com.tomorrowdevs.exercise_tracker.users.model.api.UserResponse;

import java.util.List;

public interface UserReader {

   List<UserResponse> read();

}
