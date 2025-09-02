package com.tomorrowdevs.exercise_tracker.controller;

import com.tomorrowdevs.exercise_tracker.error.ApiError;
import com.tomorrowdevs.exercise_tracker.model.api.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserReaderControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Should Return a list of user")
    void readUsers_whenDataIsInDB_shouldReturnUsers() throws Exception {

        // Arrange
        HttpEntity <String> requestEntity = buildRequest();

        // Act
        ResponseEntity <List<UserResponse>> createdUserResponse = triggerUsersRequest(requestEntity);
        List<UserResponse> createdUserDetails = createdUserResponse.getBody();

        // Assert
        assertEquals(HttpStatus.OK, createdUserResponse.getStatusCode());
        assertNotNull(createdUserDetails);

    }

    @Test
    @DisplayName("Should Return error")
    void readUsers_whenNoDataIsInDB_shouldReturnError() throws Exception {

        // Arrange
        HttpEntity <String> requestEntity = buildRequest();

        // Act
        ResponseEntity <ApiError> createdUserResponse = triggerError(requestEntity);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, createdUserResponse.getStatusCode());

    }


    public HttpEntity<String> buildRequest() {

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        return new HttpEntity<>(requestHeaders);
    }


    public ResponseEntity<List<UserResponse>> triggerUsersRequest(HttpEntity <String> requestEntity){
        return testRestTemplate
                .exchange("/api/users",
                          HttpMethod.GET,
                          requestEntity,
                          new ParameterizedTypeReference<List<UserResponse>>() {
                          });
    }

    public ResponseEntity<ApiError> triggerError(HttpEntity <String> requestEntity){
        return testRestTemplate
                .exchange("/api/users",
                          HttpMethod.GET,
                          requestEntity,
                          ApiError.class);
    }
}