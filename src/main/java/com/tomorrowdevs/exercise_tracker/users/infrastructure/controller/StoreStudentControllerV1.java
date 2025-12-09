package com.tomorrowdevs.exercise_tracker.users.infrastructure.controller;

import com.tomorrowdevs.exercise_tracker.users.application.service.StudentWriter;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Student;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.controller.request.StudentCreationRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/student")
public class StoreStudentControllerV1 {

    @Autowired
    StudentWriter studentWriter;

    @PostMapping()
    public ResponseEntity<Student> storeNewUserV1(
            @RequestBody
            @Valid
            StudentCreationRequest studentCreationRequest
    ) {
        Student newStudent = Student.create(studentCreationRequest.getUsername().getValue());
        return ResponseEntity.ok(studentWriter.save(newStudent));
    }

}
