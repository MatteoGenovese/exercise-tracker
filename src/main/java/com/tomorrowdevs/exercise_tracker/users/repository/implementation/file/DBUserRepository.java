package com.tomorrowdevs.exercise_tracker.users.repository.implementation.file;

import com.tomorrowdevs.exercise_tracker.users.model.api.UserJpaEntity;
import com.tomorrowdevs.exercise_tracker.users.model.domain.User;
import com.tomorrowdevs.exercise_tracker.users.repository.UserDBRepository;
import com.tomorrowdevs.exercise_tracker.users.repository.jpa.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class DBUserRepository implements UserDBRepository {

    @Autowired
    private UserJpaRepository userJpaRepository;

    public List <UserJpaEntity> read() {
        return userJpaRepository.findAll();
    }

    public UserJpaEntity save(User user) {
        return userJpaRepository.save(UserJpaEntity.create(user.username(),
                                                           user.uuid().toString()));
    }

}
