package com.tomorrowdevs.exercise_tracker.users.service.implementation;


import com.tomorrowdevs.exercise_tracker.users.error.DataNotFoundException;
import com.tomorrowdevs.exercise_tracker.users.model.api.UserJpaEntity;
import com.tomorrowdevs.exercise_tracker.users.model.domain.User;
import com.tomorrowdevs.exercise_tracker.users.repository.UserFileRepository;
import com.tomorrowdevs.exercise_tracker.users.repository.jpa.UserJpaRepository;
import com.tomorrowdevs.exercise_tracker.users.service.UserReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserReaderImpl implements UserReader {

    private final UserFileRepository userRepository;
    private final UserJpaRepository userJpaRepository;

    @Autowired
    public UserReaderImpl(UserFileRepository userRepository, UserJpaRepository userJpaRepository) {
        this.userRepository = userRepository;
        this.userJpaRepository = userJpaRepository;
    }

    public List <User> read() {
        List <User> userList = userRepository.read();
        List <UserJpaEntity> userJpaEntities = userJpaRepository.findAll();
        ifListIsEmptyThrowError(userList);
        return userList;
    }

    private void ifListIsEmptyThrowError(List <User> userList) {
        if( userList.isEmpty() ) {
            throw new DataNotFoundException("Data not found");
        }
    }

}
