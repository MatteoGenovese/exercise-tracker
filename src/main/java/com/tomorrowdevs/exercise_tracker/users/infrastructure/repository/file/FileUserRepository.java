package com.tomorrowdevs.exercise_tracker.users.infrastructure.repository.file;

import com.tomorrowdevs.exercise_tracker.users.application.repository.UserRepository;
import com.tomorrowdevs.exercise_tracker.users.domain.model.User;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.utils.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
@ConditionalOnProperty(name = "app.repository.type", havingValue = "file")
public class FileUserRepository implements UserRepository {

    @Autowired
    private FileHandler fileHandler;

    @Override public List <User> read() {
        return fileHandler.read();
    }

    @Override public void save(User user) {
        fileHandler.save(user);
    }

    @Override public User findUserByUuid(UUID uuid) {
        return null;
    }

    @Override public User editUserByUuid(User user) {
        return null;
    }

}
