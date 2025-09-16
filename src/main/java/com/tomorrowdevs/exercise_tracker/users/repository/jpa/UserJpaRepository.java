package com.tomorrowdevs.exercise_tracker.users.repository.jpa;

import com.tomorrowdevs.exercise_tracker.users.model.api.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserJpaRepository extends JpaRepository <UserJpaEntity, Long> {

}
