package com.tomorrowdevs.exercise_tracker.users.infrastructure.repository.db;

import com.tomorrowdevs.exercise_tracker.common.domain.EntityNotFound;
import com.tomorrowdevs.exercise_tracker.users.application.repository.StudentRepository;
import com.tomorrowdevs.exercise_tracker.users.domain.error.StudentNotFound;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Student;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.repository.jpa.StudentEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
@ConditionalOnProperty(name = "app.repository.type", havingValue = "db")
public class DBStudentRepository implements StudentRepository {

    @Autowired
    private StudentJpaRepository studentJpaRepository;

    public List<Student> read() {
        List<StudentEntity> userList = studentJpaRepository.findAll();
        return toDomain(userList);
    }

    public void save(Student student) {
        studentJpaRepository.save(StudentEntity.create(
                student.username().getValue(),
                student.uuid()
        ));
    }

    @Override
    public Student findByUuid(UUID uuid) {
        StudentEntity memorized = studentJpaRepository.findByUuid(uuid).orElseThrow(StudentNotFound::uuidNotFound);
        return toDomain(memorized);
    }

    @Transactional
    @Override
    public void editByUuid(Student student) {
        Optional<StudentEntity> studentEntity = studentJpaRepository.findByUuid(student.uuid());

        if (studentEntity.isEmpty()){
            throw EntityNotFound.uuidNotFound(student);
        }

        studentEntity.get().setUsername(student.username().getValue());
    }

    private List<Student> toDomain(List<StudentEntity> userJpaEntities) {
        return userJpaEntities.stream().map(userResponse -> Student.create(
                userResponse.getUuid(),
                userResponse.getUsername()
        )).toList();
    }

    private Student toDomain(StudentEntity studentEntity) {
        return Student.create(
                studentEntity.getUuid(),
                studentEntity.getUsername()
        );
    }
}
