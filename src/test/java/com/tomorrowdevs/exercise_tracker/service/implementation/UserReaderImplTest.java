package com.tomorrowdevs.exercise_tracker.service.implementation;

import com.tomorrowdevs.exercise_tracker.error.DataNotFoundException;
import com.tomorrowdevs.exercise_tracker.model.api.UserResponse;
import com.tomorrowdevs.exercise_tracker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserReaderImplTest {

    @InjectMocks
    private UserReaderImpl userReader;

    @Mock
    private UserRepository repository;

    private UserResponse user1;
    private UserResponse user2;
    private List <UserResponse> userListMock;

    @BeforeEach
    void setup(){
        // Arrange
        user1 = new UserResponse("testtest1");
        user2 = new UserResponse("testtest2");
        userListMock = List.of(user1, user2);
    }

    @Test
    @DisplayName("should return Users")
    void readUser_whenUserArePresent_shouldReturnUser(){

        // Arrange
        when(repository.read()).thenReturn(userListMock);

        // Act
        List <UserResponse> userList = userReader.read();

        // Assert
        assertNotNull(userList, "should not be null");
        assertEquals(2, userList.size(), "the size should be 2");
        verify(repository).read();

    }


    @Test
    @DisplayName("should throw error")
    void readUser_whenThereIsAnyUser_shouldThrowError(){

        // Arrange
        when(repository.read()).thenReturn(List.of());

        // Act

        // Assert
        assertThrows(
                DataNotFoundException.class,
                ()-> userReader.read(),
                "Should throw DataNotFoundException due the lack of data"
        );

    }

}