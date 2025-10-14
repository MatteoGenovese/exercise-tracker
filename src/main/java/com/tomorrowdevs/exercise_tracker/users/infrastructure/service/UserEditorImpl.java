package com.tomorrowdevs.exercise_tracker.users.infrastructure.service;

import com.tomorrowdevs.exercise_tracker.users.application.repository.UserRepository;
import com.tomorrowdevs.exercise_tracker.users.application.service.UserEditor;
import com.tomorrowdevs.exercise_tracker.users.domain.model.User;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.controller.request.UserEditRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserEditorImpl implements UserEditor {

    private UserRepository userRepository;

    @Autowired
    public UserEditorImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override public User edit(User user) {
        return userRepository.editUserByUuid(user);
    }
}
