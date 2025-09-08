package com.tomorrowdevs.exercise_tracker.users.service.implementation;

import com.tomorrowdevs.exercise_tracker.users.model.api.UserResponse;
import com.tomorrowdevs.exercise_tracker.users.model.domain.User;
import com.tomorrowdevs.exercise_tracker.users.repository.UserRepository;
import com.tomorrowdevs.exercise_tracker.users.service.UserSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserWriterImpl implements UserSaver {


    @Autowired
    private UserRepository userRepository;

    public UserWriterImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public UserResponse save(User user) {
        User newUser = User.create(user.username());
        return userRepository.save(newUser);
    }

}
