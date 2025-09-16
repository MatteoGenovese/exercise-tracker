package com.tomorrowdevs.exercise_tracker.users.service.implementation;

import com.tomorrowdevs.exercise_tracker.users.model.domain.User;
import com.tomorrowdevs.exercise_tracker.users.repository.UserDBRepository;
import com.tomorrowdevs.exercise_tracker.users.repository.UserFileRepository;
import com.tomorrowdevs.exercise_tracker.users.service.UserSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserWriterImpl implements UserSaver {


    @Autowired
    private UserFileRepository fileUserRepository;

    @Autowired
    private UserDBRepository userDBRepository;

    public User save(User user) {
        User newUser = User.create(user.username());
        userDBRepository.save(newUser);
        return fileUserRepository.save(newUser);
    }

}
