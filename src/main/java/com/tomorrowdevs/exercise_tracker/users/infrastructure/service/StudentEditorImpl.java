package com.tomorrowdevs.exercise_tracker.users.infrastructure.service;

import com.tomorrowdevs.exercise_tracker.users.application.repository.StudentRepository;
import com.tomorrowdevs.exercise_tracker.users.application.service.StudentEditor;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentEditorImpl implements StudentEditor {

    private StudentRepository studentRepository;

    @Autowired
    public StudentEditorImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override public Student edit(Student student) {
        return studentRepository.editStudentByUuid(student);
    }
}
