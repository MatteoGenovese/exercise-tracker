package com.tomorrowdevs.exercise_tracker.users.infrastructure.controller;

import com.tomorrowdevs.exercise_tracker.users.application.service.StudentWriter;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Student;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Username;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.controller.request.StudentCreationRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class StoreStudentControllerV1Test {

    @InjectMocks
    private StoreStudentControllerV1 storeStudentControllerV1;

    @Mock
    private StudentWriter studentWriter;

    @Test
    @DisplayName("should store new user")
    void createUser_whenUserIsValid_shouldCreateIt() {

        // Arrange
        Student student = Student.create("username1");

        // Act
        when(studentWriter.save(Mockito.any(Student.class))).thenReturn(student);
        ResponseEntity <Student>
                user1 = storeStudentControllerV1.storeNewUserV1(new StudentCreationRequest(new Username("username1")));

        // Assert
        verify(studentWriter).save(Mockito.any(Student.class));
        assertEquals(user1.getBody().username(), student.username());

    }

}