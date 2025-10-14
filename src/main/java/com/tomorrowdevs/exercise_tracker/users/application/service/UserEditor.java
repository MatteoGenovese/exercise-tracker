package com.tomorrowdevs.exercise_tracker.users.application.service;

import com.tomorrowdevs.exercise_tracker.users.domain.model.User;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.controller.request.UserEditRequest;


public interface UserEditor {

    User edit(User user);
}
