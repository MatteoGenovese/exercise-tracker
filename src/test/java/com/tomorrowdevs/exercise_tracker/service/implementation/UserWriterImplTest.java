package com.tomorrowdevs.exercise_tracker.service.implementation;

import com.tomorrowdevs.exercise_tracker.model.api.UserRequest;
import com.tomorrowdevs.exercise_tracker.model.api.UserResponse;
import com.tomorrowdevs.exercise_tracker.model.domain.UserDomain;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserWriterImplTest {

    @InjectMocks
    private UserWriterImpl userWriter;

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
    @DisplayName("should insert new User")
    void createUser_whenUsernameIsValid_thenWriteItOnFile(){

        // Arrange
        when(repository.save(any(UserDomain.class))).thenReturn(user1);

        // Act
        UserResponse response = userWriter.save(new UserRequest(user1.getUserName()));

        // Assert
        assertNotNull(response.getUserName());

    }

}