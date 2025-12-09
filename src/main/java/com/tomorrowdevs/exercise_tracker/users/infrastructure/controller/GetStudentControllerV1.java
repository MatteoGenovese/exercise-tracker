package com.tomorrowdevs.exercise_tracker.users.infrastructure.controller;

import com.tomorrowdevs.exercise_tracker.users.application.service.StudentReader;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Student;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.controller.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/v1/student")
public class GetStudentControllerV1 {

    @Autowired
    StudentReader studentReader;

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getUserListV1() {
        return ResponseEntity.ok(mapResponse(studentReader.read()));
    }

    private List<StudentResponse> mapResponse(List<Student> studentList) {
        return studentList.stream()
                .map(student -> StudentResponse.create(
                        student.username(),
                        student.uuid().toString()
                ))
                .toList();
    }

}
