package com.tomorrowdevs.exercise_tracker.users.infrastructure.repository.db;

import com.tomorrowdevs.exercise_tracker.users.infrastructure.repository.jpa.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface StudentJpaRepository extends JpaRepository <StudentEntity, Long> {

    Optional<StudentEntity> findByUuid(UUID uuid);

}
