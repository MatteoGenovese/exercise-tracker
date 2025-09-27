package com.tomorrowdevs.exercise_tracker.users.application.service;

import com.tomorrowdevs.exercise_tracker.users.domain.model.User;


public interface UserWriter {

    public User save(User user);

}
