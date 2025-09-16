package com.tomorrowdevs.exercise_tracker.users.repository;


import com.tomorrowdevs.exercise_tracker.users.model.api.UserJpaEntity;
import com.tomorrowdevs.exercise_tracker.users.model.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDBRepository {

    List <UserJpaEntity> read();

    UserJpaEntity save(User user);
}
