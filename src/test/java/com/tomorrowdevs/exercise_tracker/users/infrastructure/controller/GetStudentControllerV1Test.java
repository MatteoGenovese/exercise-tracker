package com.tomorrowdevs.exercise_tracker.users.infrastructure.controller;

import com.tomorrowdevs.exercise_tracker.users.domain.model.Student;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.service.StudentReaderImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class GetStudentControllerV1Test {

    @InjectMocks
    private GetStudentControllerV1 getStudentControllerV1;

    @Mock
    private StudentReaderImpl studentReaderImpl;

    @Test
    @DisplayName("should get students")
    void getUser_whenUserIsInRepository_shouldReturnIt() {

        // Arrange
        Student student1 = Student.create("username1");
        Student student2 = Student.create("username2");
        List <Student> studentList = List.of(
                student1,
                student2
        );

        // Act
        when(studentReaderImpl.read()).thenReturn(studentList);
        getStudentControllerV1.getUserListV1();

        // Assert
        verify(studentReaderImpl).read();

    }


}