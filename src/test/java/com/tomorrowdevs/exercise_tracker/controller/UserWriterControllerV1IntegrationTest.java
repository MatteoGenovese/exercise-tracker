package com.tomorrowdevs.exercise_tracker.controller;

import com.tomorrowdevs.exercise_tracker.users.model.api.UserJpaEntity;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserWriterControllerV1IntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Should Return User if username length > 8 ")
    void createUser_whenDataAreCorrect_thenShouldReturnUser() throws Exception {

        // Arrange
        JSONObject userRequestJson = new JSONObject();
        userRequestJson.put("userName","testtest1");
        HttpEntity <String> requestEntity = buildRequest(userRequestJson);

        // Act
        ResponseEntity <UserJpaEntity> createdUserResponse = triggerRequest(requestEntity);
        UserJpaEntity createdUserDetails = createdUserResponse.getBody();

        // Assert
        assertEquals(HttpStatus.OK, createdUserResponse.getStatusCode());
        assertEquals(userRequestJson.getAsString("userName"),
                                createdUserDetails.getUserName(),
                                "Returned username is incorrect");

    }



    @Test
    @DisplayName("Should Return error if username length < 8 ")
    void createUser_whenDataAreInvalid_thenShouldReturnError() throws Exception {

        // Arrange
        JSONObject userRequestJson = new JSONObject();
        userRequestJson.put("userName","short");
        HttpEntity <String> requestEntity = buildRequest(userRequestJson);

        // Act
        ResponseEntity <UserJpaEntity> createdUserResponse = triggerRequest(requestEntity);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, createdUserResponse.getStatusCode());

    }


    public HttpEntity<String> buildRequest(JSONObject userRequestJson) {

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        return new HttpEntity<>(userRequestJson.toString(), requestHeaders);
    }


    public ResponseEntity<UserJpaEntity> triggerRequest(HttpEntity <String> requestEntity){
        return testRestTemplate
                .postForEntity("/api/users",
                               requestEntity,
                               UserJpaEntity.class);
    }
}