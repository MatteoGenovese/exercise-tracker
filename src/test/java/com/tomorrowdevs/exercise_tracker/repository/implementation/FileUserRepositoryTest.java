package com.tomorrowdevs.exercise_tracker.repository.implementation;

import com.tomorrowdevs.exercise_tracker.users.model.api.UserRequest;
import com.tomorrowdevs.exercise_tracker.users.model.domain.User;
import com.tomorrowdevs.exercise_tracker.users.repository.implementation.file.FileUserRepositoryImpl;
import com.tomorrowdevs.exercise_tracker.users.utils.FileHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;


@ExtendWith(MockitoExtension.class)
class FileUserRepositoryTest {

    @InjectMocks
    private FileUserRepositoryImpl fileUserRepository;

    @Mock
    private FileHandler fileHandler;

    User userResponse1;
    User userResponse2;

    @BeforeEach
    void setup() {

        userResponse1 = User.create("username1", UUID.randomUUID());
        userResponse2 = User.create("username2", UUID.randomUUID());
    }

    @Test
    void saveUser() {

        // Arrange
        UserRequest userRequest = new UserRequest("Username");
        User user = User.create(userRequest.getUsername());

        // Act
        Mockito.when(fileHandler.save(Mockito.any(User.class))).thenReturn(User.create(
                "Username", UUID.randomUUID()));
        User response = fileUserRepository.save(user);

        // Assert
        Assertions.assertEquals(response.username(), userRequest.getUsername());
        Mockito.verify(fileHandler).save(Mockito.any());
    }


    @Test
    void readUsers() {

        // Arrange
        List <User> userResponseList = List.of(userResponse1, userResponse2);

        // Act
        Mockito.when(fileHandler.read()).thenReturn(userResponseList);
        List <User> response = fileUserRepository.read();

        // Assert
        Mockito.verify(fileHandler).read();
        Assertions.assertEquals(userResponse1.username(), response.get(0).username());
        Assertions.assertEquals(userResponse2.username(), response.get(1).username());
    }

}