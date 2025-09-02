package com.tomorrowdevs.exercise_tracker.service.implementation;

import com.tomorrowdevs.exercise_tracker.model.api.UserRequest;
import com.tomorrowdevs.exercise_tracker.model.api.UserResponse;
import com.tomorrowdevs.exercise_tracker.model.domain.UserDomain;
import com.tomorrowdevs.exercise_tracker.repository.UserRepository;
import com.tomorrowdevs.exercise_tracker.service.UserWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserWriterImpl implements UserWriter {


    @Autowired
    private UserRepository userRepository;

    public UserResponse save(UserRequest user) {
        UserDomain userDomain = new UserDomain(user.getUsername());
        return userRepository.save(userDomain);
    }

}
