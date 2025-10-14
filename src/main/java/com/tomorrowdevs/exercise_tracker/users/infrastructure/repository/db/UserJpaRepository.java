package com.tomorrowdevs.exercise_tracker.users.infrastructure.repository.db;

import com.tomorrowdevs.exercise_tracker.users.domain.model.User;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.repository.jpa.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserJpaRepository extends JpaRepository <UserJpaEntity, Long> {
    Optional<UserJpaEntity> findByUuid(String uuid);

}
