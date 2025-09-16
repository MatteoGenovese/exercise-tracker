package com.tomorrowdevs.exercise_tracker.users.repository;

import com.tomorrowdevs.exercise_tracker.users.model.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserFileRepository {

    List <User> read();

    User save(User user);
}
