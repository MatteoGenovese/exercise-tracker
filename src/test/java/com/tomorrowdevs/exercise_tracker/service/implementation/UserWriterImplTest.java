package com.tomorrowdevs.exercise_tracker.service.implementation;

import com.tomorrowdevs.exercise_tracker.users.model.domain.User;
import com.tomorrowdevs.exercise_tracker.users.repository.UserFileRepository;
import com.tomorrowdevs.exercise_tracker.users.service.implementation.UserWriterImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserWriterImplTest {

    @InjectMocks
    private UserWriterImpl userWriter;

    @Mock
    private UserFileRepository persister;

    private User user1;
    private User user2;
    private List <User> userListMock;


    @BeforeEach
    void setup() {
        // Arrange
        user1 = User.create("testtest1", UUID.randomUUID());
        user2 = User.create("testtest2", UUID.randomUUID());
        userListMock = List.of(user1, user2);
    }

    @Test
    @DisplayName("should insert new User")
    void createUser_whenUsernameIsValid_thenWriteItOnFile() {

        // Arrange
        when(persister.save(any(User.class))).thenReturn(user1);

        // Act
        User response = userWriter.save(User.create(user1.username()));

        // Assert
        assertNotNull(response.username());

    }

}