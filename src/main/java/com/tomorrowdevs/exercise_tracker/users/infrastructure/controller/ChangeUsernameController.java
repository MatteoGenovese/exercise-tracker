package com.tomorrowdevs.exercise_tracker.users.infrastructure.controller;

import com.tomorrowdevs.exercise_tracker.users.application.service.StudentEditor;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Student;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.controller.request.ChangeUsernameRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/students")
public class ChangeUsernameController {

    @Autowired
    StudentEditor studentEditor;

    @PutMapping()
    public ResponseEntity<Student> changeStudentUsernameV1(
            @RequestBody
            @Valid
            ChangeUsernameRequest changeUsernameRequest
    ) {

        Student newStudent = Student.create(changeUsernameRequest.getUsername().getValue());
        return ResponseEntity.ok(studentEditor.edit(newStudent));
    }

}
