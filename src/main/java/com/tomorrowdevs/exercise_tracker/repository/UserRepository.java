package com.tomorrowdevs.exercise_tracker.repository;

import com.tomorrowdevs.exercise_tracker.model.UserResponse;

import java.util.List;

public interface UserRepository {

    List<UserResponse> read();


}
