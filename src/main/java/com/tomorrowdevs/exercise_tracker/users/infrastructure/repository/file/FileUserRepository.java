package com.tomorrowdevs.exercise_tracker.users.infrastructure.repository.file;

import com.tomorrowdevs.exercise_tracker.users.application.repository.UserRepository;
import com.tomorrowdevs.exercise_tracker.users.domain.model.User;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.utils.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@ConditionalOnProperty(name = "app.repository.type", havingValue = "file")
public class FileUserRepository implements UserRepository {

    @Autowired
    private FileHandler fileHandler;

    @Override public List <User> read() {
        return fileHandler.read();
    }

    @Override public User save(User user) {
        return fileHandler.save(user);
    }

}
