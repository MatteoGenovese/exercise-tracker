package com.tomorrowdevs.exercise_tracker.users.infrastructure.repository.file;

import com.tomorrowdevs.exercise_tracker.users.domain.model.Student;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Username;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.controller.request.StudentCreationRequest;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.utils.FileHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;


@ExtendWith(MockitoExtension.class)
class FileStudentRepositoryTest {

    @InjectMocks
    private FileStudentRepository fileUserRepository;

    @Mock
    private FileHandler fileHandler;

    @Test
    void saveUser() {

        // Arrange
        StudentCreationRequest studentCreationRequest = new StudentCreationRequest(new Username("Username"));
        Student student = Student.create(studentCreationRequest.getUsername().getValue());

        // Act
        Mockito.when(fileHandler.save(Mockito.any(Student.class))).thenReturn(student);

        // Assert
//        Assertions.assertEquals(response.username().getValue(), studentCreationRequest.getUsername().getValue());
//        Mockito.verify(fileHandler).save(Mockito.any());
    }


    @Test
    void readStudents() {

        // Arrange

        Student student1 = new Student(UUID.randomUUID(), new Username("testtest1"));
        Student student2 = new Student(UUID.randomUUID(), new Username("testtest2"));
        List <Student> studentResponseList = List.of(
                student1,
                student2
        );

        // Act
        Mockito.when(fileHandler.read()).thenReturn(studentResponseList);
        List <Student> response = fileUserRepository.read();

        // Assert
        Mockito.verify(fileHandler).read();
        Assertions.assertEquals(student1.username().getValue(), response.get(0).username().getValue());
        Assertions.assertEquals(student2.username().getValue(), response.get(1).username().getValue());
    }

}