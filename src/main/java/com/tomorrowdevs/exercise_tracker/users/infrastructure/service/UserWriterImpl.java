package com.tomorrowdevs.exercise_tracker.users.infrastructure.service;

import com.tomorrowdevs.exercise_tracker.users.application.repository.UserRepository;
import com.tomorrowdevs.exercise_tracker.users.application.service.UserWriter;
import com.tomorrowdevs.exercise_tracker.users.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserWriterImpl implements UserWriter {

    private final UserRepository userRepository;

    @Autowired
    public UserWriterImpl(
            UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return storeUserInDbOrFile(user);
    }

    private User storeUserInDbOrFile(User user) {
        return userRepository.save(user);
    }

}

