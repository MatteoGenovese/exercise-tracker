package com.tomorrowdevs.exercise_tracker.service.implementation;

import com.tomorrowdevs.exercise_tracker.model.UserRequest;
import com.tomorrowdevs.exercise_tracker.model.UserResponse;
import com.tomorrowdevs.exercise_tracker.service.UserWriter;
import org.springframework.stereotype.Service;

@Service
public class UserWriterImpl implements UserWriter {


    public UserResponse save(UserRequest user) {
        return new UserResponse(user.getUsername());
    }

}
