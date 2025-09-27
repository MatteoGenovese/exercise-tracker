package com.tomorrowdevs.exercise_tracker.users.application.repository;

import com.tomorrowdevs.exercise_tracker.users.domain.model.User;

import java.util.List;


public interface UserRepository {
    List <User> read();

    User save(User user);
}
