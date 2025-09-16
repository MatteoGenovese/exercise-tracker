package com.tomorrowdevs.exercise_tracker.users.repository.implementation.file;

import com.tomorrowdevs.exercise_tracker.users.model.domain.User;
import com.tomorrowdevs.exercise_tracker.users.repository.UserFileRepository;
import com.tomorrowdevs.exercise_tracker.users.utils.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileUserRepositoryImpl implements UserFileRepository {

    @Autowired
    private FileHandler fileHandler;

    @Override public List <User> read() {
        return fileHandler.read();
    }

    @Override public User save(User user) {
        return fileHandler.save(user);
    }

}
