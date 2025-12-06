package com.tomorrowdevs.exercise_tracker.users.infrastructure.repository.file;

import com.tomorrowdevs.exercise_tracker.users.domain.model.User;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Username;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.controller.request.UserCreationRequest;
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
class FileUserRepositoryTest {

    @InjectMocks
    private FileUserRepository fileUserRepository;

    @Mock
    private FileHandler fileHandler;

    @Test
    void saveUser() {

        // Arrange
        UserCreationRequest userCreationRequest = new UserCreationRequest(new Username("Username"));
        User user = User.create(userCreationRequest.getUsername().getValue());

        // Act
        Mockito.when(fileHandler.save(Mockito.any(User.class))).thenReturn(user);
//        User response = fileUserRepository.save(user);

        // Assert
//        Assertions.assertEquals(response.username().getValue(), userCreationRequest.getUsername().getValue());
//        Mockito.verify(fileHandler).save(Mockito.any());
    }


    @Test
    void readUsers() {

        // Arrange

        User user1 = new User(UUID.randomUUID(), new Username("testtest1"));
        User user2 = new User(UUID.randomUUID(), new Username("testtest2"));
        List <User> userResponseList = List.of(user1, user2);

        // Act
        Mockito.when(fileHandler.read()).thenReturn(userResponseList);
        List <User> response = fileUserRepository.read();

        // Assert
        Mockito.verify(fileHandler).read();
        Assertions.assertEquals(user1.username().getValue(), response.get(0).username().getValue());
        Assertions.assertEquals(user2.username().getValue(), response.get(1).username().getValue());
    }

}