package com.tomorrowdevs.exercise_tracker.users.infrastructure.service;

import com.tomorrowdevs.exercise_tracker.users.application.repository.StudentRepository;
import com.tomorrowdevs.exercise_tracker.users.application.service.StudentReader;
import com.tomorrowdevs.exercise_tracker.users.domain.error.StudentEmptyList;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Student;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.error.DataNotFoundError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentReaderImpl implements StudentReader {

    private StudentRepository studentRepository;

    @Autowired
    public StudentReaderImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List <Student> read() throws DataNotFoundError {
        List <Student> students = studentRepository.read();

        ifListIsEmptyThrowError(students);

        return students;
    }

    private void ifListIsEmptyThrowError(List <Student> students) throws DataNotFoundError {
        if( students.isEmpty() ) {
            throw new StudentEmptyList("Data not found");
        }
    }
}
