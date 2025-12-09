package com.tomorrowdevs.exercise_tracker.users.infrastructure.service;

import com.tomorrowdevs.exercise_tracker.users.application.repository.StudentRepository;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Student;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Username;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.error.DataNotFoundError;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.props.ApplicationProps;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentReaderImplTest {

    @InjectMocks
    private StudentReaderImpl studentReader;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ApplicationProps applicationProps;


    @Test
    @DisplayName("should throw error from file")
    @Order(1)
    void readStudent_whenThereIsAnyStudent_shouldThrowError() {

        // Arrange

        // Assert
        assertThrows(
                DataNotFoundError.class,
                () -> studentReader.read(),
                "Should throw DataNotFoundException due the lack of data"
        );

    }

    @Test
    @DisplayName("should return Students ")
    @Order(2)
    void readStudent_whenStudentArePresentOnAFile_shouldReturnStudent() {

        // Arrange
        Student student1 = new Student(UUID.randomUUID(), new Username("testtest1"));
        Student student2 = new Student(UUID.randomUUID(), new Username("testtest2"));
        List <Student> studentListMock = List.of(
                student1,
                student2
        );

        when(studentRepository.read()).thenReturn(studentListMock);

        // Act
        List <Student> studentList = studentReader.read();

        // Assert
        assertNotNull(studentList, "should not be null");
        assertEquals(2, studentList.size(), "the size should be 2");
        verify(studentRepository).read();

    }
}