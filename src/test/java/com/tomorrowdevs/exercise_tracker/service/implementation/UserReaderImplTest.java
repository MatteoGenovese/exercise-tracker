package com.tomorrowdevs.exercise_tracker.service.implementation;

import com.tomorrowdevs.exercise_tracker.users.error.DataNotFoundException;
import com.tomorrowdevs.exercise_tracker.users.model.api.UserJpaEntity;
import com.tomorrowdevs.exercise_tracker.users.repository.UserFileRepository;
import com.tomorrowdevs.exercise_tracker.users.service.implementation.UserReaderImpl;
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
    private UserFileRepository repository;

    private UserJpaEntity user1;
    private UserJpaEntity user2;
    private List <UserJpaEntity> userListMock;

    @BeforeEach
    void setup(){
        // Arrange
        user1 = new UserJpaEntity("testtest1", "1");
        user2 = new UserJpaEntity("testtest2", "2");
        userListMock = List.of(user1, user2);
    }

    @Test
    @DisplayName("should return Users")
    void readUser_whenUserArePresent_shouldReturnUser(){

        // Arrange
        when(repository.read()).thenReturn(userListMock);

        // Act
        List <UserJpaEntity> userList = userReader.read();

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