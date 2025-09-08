package com.tomorrowdevs.exercise_tracker.users.repository;

import com.tomorrowdevs.exercise_tracker.users.model.api.UserResponse;
import com.tomorrowdevs.exercise_tracker.users.model.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    List<UserResponse> read();
    UserResponse save(User user);


}
