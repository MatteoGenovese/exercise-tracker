package com.tomorrowdevs.exercise_tracker.users.infrastructure.repository.db;

import com.tomorrowdevs.exercise_tracker.users.infrastructure.repository.jpa.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserJpaRepository extends JpaRepository <UserEntity, Long> {

    Optional<UserEntity> findByUuid(UUID uuid);

}
