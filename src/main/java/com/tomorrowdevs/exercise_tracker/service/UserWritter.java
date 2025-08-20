package com.tomorrowdevs.exercise_tracker.service;

import com.tomorrowdevs.exercise_tracker.model.UserRequest;
import com.tomorrowdevs.exercise_tracker.model.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserWritter {


    public UserResponse save(UserRequest user) {
        return new UserResponse(user.getUsername());
    }

}
