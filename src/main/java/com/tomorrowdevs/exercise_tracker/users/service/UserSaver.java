package com.tomorrowdevs.exercise_tracker.users.service;

import com.tomorrowdevs.exercise_tracker.users.model.domain.User;


public interface UserSaver {

    User save(User user);

}
