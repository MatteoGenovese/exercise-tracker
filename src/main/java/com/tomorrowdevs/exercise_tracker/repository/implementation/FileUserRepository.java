package com.tomorrowdevs.exercise_tracker.repository.implementation;

import com.tomorrowdevs.exercise_tracker.model.api.UserResponse;
import com.tomorrowdevs.exercise_tracker.model.domain.UserDomain;
import com.tomorrowdevs.exercise_tracker.model.persistence.UserEntity;
import com.tomorrowdevs.exercise_tracker.repository.UserRepository;
import com.tomorrowdevs.exercise_tracker.utils.FileHandler;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileUserRepository implements UserRepository {

    private FileHandler fileHandler;

    @Override public List <UserResponse> read() {

        return fileHandler.read();
    }

    @Override public UserResponse save(UserDomain user) {

        UserEntity userEntity = new UserEntity(user.getUserName(), user.getUuid());
        return fileHandler.save(userEntity);
    }

}
