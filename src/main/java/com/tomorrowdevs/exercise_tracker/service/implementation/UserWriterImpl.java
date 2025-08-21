package com.tomorrowdevs.exercise_tracker.service.implementation;

import com.tomorrowdevs.exercise_tracker.model.UserRequest;
import com.tomorrowdevs.exercise_tracker.model.UserResponse;
import com.tomorrowdevs.exercise_tracker.repository.UserRepository;
import com.tomorrowdevs.exercise_tracker.service.UserWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserWriterImpl implements UserWriter {


    @Autowired
    private UserRepository userRepository;

    public UserResponse save(UserRequest user) {
        return userRepository.save(user);
    }

}
