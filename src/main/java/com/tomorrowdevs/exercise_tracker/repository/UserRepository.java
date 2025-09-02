package com.tomorrowdevs.exercise_tracker.repository;

import com.tomorrowdevs.exercise_tracker.model.api.UserResponse;
import com.tomorrowdevs.exercise_tracker.model.domain.UserDomain;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    List<UserResponse> read();
    UserResponse save(UserDomain userRequest);


}
