package com.tomorrowdevs.exercise_tracker.users.infrastructure.service;

import com.tomorrowdevs.exercise_tracker.users.application.repository.StudentRepository;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Student;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Username;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentWriterImplTest {

    @InjectMocks
    private StudentWriterImpl studentWriter;

    @Mock
    private StudentRepository studentRepository;


    @Test
    @DisplayName("should insert new User on file")
    void createUser_whenUsernameIsValid_thenWriteItOnFile() {

        // Arrange
        Student student1 = new Student(UUID.randomUUID(), new Username("testtest1"));

        // Act
        Student response = studentWriter.save(student1);


        // Assert
        assertNotNull(response.username());
        assertEquals(response.username(), student1.username());
        verify(studentRepository).save(argThat(u -> u.username().equals(student1.username())));

        verifyNoMoreInteractions(studentRepository);

    }

}