package com.tomorrowdevs.exercise_tracker.users.infrastructure.service;

import com.tomorrowdevs.exercise_tracker.users.application.repository.UserRepository;
import com.tomorrowdevs.exercise_tracker.users.domain.model.User;
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
class UserReaderImplTest {

    @InjectMocks
    private UserReaderImpl userReader;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ApplicationProps applicationProps;


    @Test
    @DisplayName("should throw error from file")
    @Order(1)
    void readUser_whenThereIsAnyUser_shouldThrowError() {

        // Arrange
        //        when(userRepository.read()).thenReturn(List.of());

        // Assert
        assertThrows(
                DataNotFoundError.class,
                () -> userReader.read(),
                "Should throw DataNotFoundException due the lack of data"
        );

    }

    @Test
    @DisplayName("should return Users ")
    @Order(2)
    void readUser_whenUserArePresentOnAFile_shouldReturnUser() {

        // Arrange
        User user1 = new User(UUID.randomUUID(), new Username("testtest1"));
        User user2 = new User(UUID.randomUUID(), new Username("testtest2"));
        List <User> userListMock = List.of(user1, user2);

        when(userRepository.read()).thenReturn(userListMock);

        // Act
        List <User> userList = userReader.read();

        // Assert
        assertNotNull(userList, "should not be null");
        assertEquals(2, userList.size(), "the size should be 2");
        verify(userRepository).read();

    }
}