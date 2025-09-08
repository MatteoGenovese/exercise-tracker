package com.tomorrowdevs.exercise_tracker.users.repository.implementation.file;

import com.tomorrowdevs.exercise_tracker.users.model.api.UserResponse;
import com.tomorrowdevs.exercise_tracker.users.model.domain.User;
import com.tomorrowdevs.exercise_tracker.users.repository.UserRepository;
import com.tomorrowdevs.exercise_tracker.users.utils.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileUserRepository implements UserRepository {

    @Autowired
    private FileHandler fileHandler;

    @Override public List <UserResponse> read() {

        return fileHandler.read();
    }

    @Override public UserResponse save(User user) {

        return fileHandler.save(user);
    }

}
