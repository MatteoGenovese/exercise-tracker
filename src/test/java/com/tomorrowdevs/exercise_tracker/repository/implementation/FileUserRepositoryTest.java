package com.tomorrowdevs.exercise_tracker.repository.implementation;

import com.tomorrowdevs.exercise_tracker.users.model.api.UserRequest;
import com.tomorrowdevs.exercise_tracker.users.model.api.UserResponse;
import com.tomorrowdevs.exercise_tracker.users.model.domain.User;
import com.tomorrowdevs.exercise_tracker.users.model.persistence.UserEntity;
import com.tomorrowdevs.exercise_tracker.users.repository.implementation.file.FileUserRepository;
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

@ExtendWith(MockitoExtension.class)
class FileUserRepositoryTest {

    @InjectMocks
    private FileUserRepository fileUserRepository;

    @Mock
    private FileHandler fileHandler;

    @BeforeEach
    void setup(){

        UserResponse userResponse1 = new UserResponse("username1", "1");
        UserResponse userResponse2 = new UserResponse("username2", "2");
    }

    @Test
    void saveUser(){

        // Arrange
        UserRequest userRequest = new UserRequest("Username");
        User user = User.create(userRequest.getUsername());

        // Act
        Mockito.when(fileHandler.save(Mockito.any(User.class))).thenReturn(new UserResponse(
                "Username", "1"));
        UserResponse response = fileUserRepository.save(user);

        // Assert
        Assertions.assertEquals(response.getUserName(), userRequest.getUsername());
        Mockito.verify(fileHandler).save(Mockito.any());
    }


    @Test
    void readUsers(){

        // Arrange
        UserResponse userResponse1 = new UserResponse("username1", "1");
        UserResponse userResponse2 = new UserResponse("username2", "2");

        List<UserResponse> userResponseList = List.of(userResponse1, userResponse2);


        // Act
        Mockito.when(fileHandler.read()).thenReturn(userResponseList);
        List <UserResponse> response = fileUserRepository.read();

        // Assert
        Mockito.verify(fileHandler).read();
        Assertions.assertEquals(userResponse1.getUserName(), response.get(0).getUserName());
        Assertions.assertEquals(userResponse2.getUserName(), response.get(1).getUserName());
    }

}