package com.tomorrowdevs.exercise_tracker.users.infrastructure.repository.file;

import com.tomorrowdevs.exercise_tracker.users.application.repository.StudentRepository;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Student;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.utils.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
@ConditionalOnProperty(name = "app.repository.type", havingValue = "file")
public class FileStudentRepository implements StudentRepository {

    @Autowired
    private FileHandler fileHandler;

    @Override public List <Student> read() {
        return fileHandler.read();
    }

    @Override public void save(Student student) {
        fileHandler.save(student);
    }

    @Override public Student findUserByUuid(UUID uuid) {
        return null;
    }

    @Override public Student editUserByUuid(Student student) {
        return null;
    }

}
