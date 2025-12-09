package com.tomorrowdevs.exercise_tracker.users.application.repository;

import com.tomorrowdevs.exercise_tracker.common.application.repository.EntityRepository;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Student;

import java.util.UUID;


public interface StudentRepository extends EntityRepository<Student> {

    Student findStudentByUuid(UUID uuid);



}
