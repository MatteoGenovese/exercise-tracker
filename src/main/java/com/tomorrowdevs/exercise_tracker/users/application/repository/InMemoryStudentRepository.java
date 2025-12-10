package com.tomorrowdevs.exercise_tracker.users.application.repository;

import com.tomorrowdevs.exercise_tracker.common.application.repository.InMemoryRepository;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(name = "app.repository.type", havingValue = "test")
public class InMemoryStudentRepository extends InMemoryRepository<Student> implements StudentRepository {

}
