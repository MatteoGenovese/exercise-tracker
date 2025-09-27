package com.tomorrowdevs.exercise_tracker.users.infrastructure.controller;

import com.tomorrowdevs.exercise_tracker.users.application.service.UserWriter;
import com.tomorrowdevs.exercise_tracker.users.domain.model.User;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Username;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.controller.request.UserRequest;
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
class StoreUserControllerV1Test {

    @InjectMocks
    private StoreUserControllerV1 storeUserControllerV1;

    @Mock
    private UserWriter userWriter;

    @Test
    @DisplayName("should store new user")
    void createUser_whenUserIsValid_shouldCreateIt() {

        // Arrange
        User user = User.create("username1");

        // Act
        when(userWriter.save(Mockito.any(User.class))).thenReturn(user);
        ResponseEntity <User>
                user1 = storeUserControllerV1.storeNewUserV1(new UserRequest(new Username("username1")));

        // Assert
        verify(userWriter).save(Mockito.any(User.class));
        assertEquals(user1.getBody().username(), user.username());

    }

}