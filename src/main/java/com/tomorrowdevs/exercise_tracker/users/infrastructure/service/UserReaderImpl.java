package com.tomorrowdevs.exercise_tracker.users.infrastructure.service;

import com.tomorrowdevs.exercise_tracker.users.application.repository.UserRepository;
import com.tomorrowdevs.exercise_tracker.users.domain.model.User;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.error.DataNotFoundError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReaderImpl {
    private final UserRepository userRepository;

    @Autowired
    public UserReaderImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List <User> read() throws DataNotFoundError {
        List <User> users = userRepository.read();

        ifListIsEmptyThrowError(users);

        return users;
    }

    private void ifListIsEmptyThrowError(List <User> users) throws DataNotFoundError {
        if( users.isEmpty() ) {
            throw new DataNotFoundError("Data not found", new Throwable("Data not found"));
        }
    }
}
