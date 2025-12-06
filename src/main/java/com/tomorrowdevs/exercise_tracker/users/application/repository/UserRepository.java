package com.tomorrowdevs.exercise_tracker.users.application.repository;

import com.tomorrowdevs.exercise_tracker.users.domain.model.User;

import java.util.List;
import java.util.UUID;


public interface UserRepository {

    List<User> read();

    void save(User user);

    User findUserByUuid(UUID uuid);

    User editUserByUuid(User user);
}
