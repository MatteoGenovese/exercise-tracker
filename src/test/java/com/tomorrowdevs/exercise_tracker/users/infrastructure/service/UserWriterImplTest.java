package com.tomorrowdevs.exercise_tracker.users.infrastructure.service;

import com.tomorrowdevs.exercise_tracker.users.application.repository.UserRepository;
import com.tomorrowdevs.exercise_tracker.users.domain.model.User;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Username;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserWriterImplTest {

    @InjectMocks
    private UserWriterImpl userWriter;

    @Mock
    private UserRepository userRepository;


    @Test
    @DisplayName("should insert new User on file")
    void createUser_whenUsernameIsValid_thenWriteItOnFile() {

        // Arrange
        User user1 = new User(UUID.randomUUID(), new Username("testtest1"));

        // Act
        when(userRepository.save(any(User.class))).thenReturn(user1);
        User response = userWriter.save(user1);


        // Assert
        assertNotNull(response.username());
        assertEquals(response.username(), user1.username());
        verify(userRepository).save(argThat(u -> u.username().equals(user1.username())));

        verifyNoMoreInteractions(userRepository);

    }

}