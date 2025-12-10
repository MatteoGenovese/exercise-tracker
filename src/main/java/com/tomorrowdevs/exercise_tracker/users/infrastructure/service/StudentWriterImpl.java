package com.tomorrowdevs.exercise_tracker.users.infrastructure.service;

import com.tomorrowdevs.exercise_tracker.users.application.repository.StudentRepository;
import com.tomorrowdevs.exercise_tracker.users.application.service.StudentWriter;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentWriterImpl implements StudentWriter {

    private StudentRepository studentRepository;

    @Autowired
    public StudentWriterImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student save(Student student) {
        storeUserInDbOrFile(student);
        return student;
    }

    private void storeUserInDbOrFile(Student student) {
        studentRepository.save(student);
    }

}

