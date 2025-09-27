package com.tomorrowdevs.exercise_tracker.users.infrastructure.repository.db;

import com.tomorrowdevs.exercise_tracker.users.infrastructure.repository.jpa.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserJpaRepository extends JpaRepository <UserJpaEntity, Long> {

}
