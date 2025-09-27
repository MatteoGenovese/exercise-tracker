package com.tomorrowdevs.exercise_tracker.users.infrastructure.controller;

import com.tomorrowdevs.exercise_tracker.users.domain.model.User;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.service.UserReaderImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class GetUserControllerV1Test {

    @InjectMocks
    private GetUserControllerV1 getUserControllerV1;

    @Mock
    private UserReaderImpl userReaderImpl;

    @Test
    @DisplayName("should get users")
    void getUser_whenUserIsInRepository_shouldReturnIt() {

        // Arrange
        User user1 = User.create("username1");
        User user2 = User.create("username2");
        List <User> userList = List.of(user1, user2);

        // Act
        when(userReaderImpl.read()).thenReturn(userList);
        getUserControllerV1.getUserListV1();

        // Assert
        verify(userReaderImpl).read();

    }


}