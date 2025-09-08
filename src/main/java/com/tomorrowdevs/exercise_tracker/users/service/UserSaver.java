package com.tomorrowdevs.exercise_tracker.users.service;

import com.tomorrowdevs.exercise_tracker.users.model.api.UserResponse;
import com.tomorrowdevs.exercise_tracker.users.model.domain.User;

public interface UserSaver {

    UserResponse save(User user);

}
