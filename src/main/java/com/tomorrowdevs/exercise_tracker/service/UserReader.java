package com.tomorrowdevs.exercise_tracker.service;


import com.tomorrowdevs.exercise_tracker.model.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReader {

    public List<UserResponse> read() {
        return List.of(new UserResponse("test"), new UserResponse("matteo"));
    }

}
