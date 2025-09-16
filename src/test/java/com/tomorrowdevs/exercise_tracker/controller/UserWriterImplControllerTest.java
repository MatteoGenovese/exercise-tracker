package com.tomorrowdevs.exercise_tracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomorrowdevs.exercise_tracker.users.controller.SaveUserControllerV1;
import com.tomorrowdevs.exercise_tracker.users.model.api.UserJpaEntity;
import com.tomorrowdevs.exercise_tracker.users.model.domain.User;
import com.tomorrowdevs.exercise_tracker.users.service.implementation.UserWriterImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(
        controllers = SaveUserControllerV1.class,
        excludeAutoConfiguration = {SecurityAutoConfiguration.class}
)
class UserWriterImplControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserWriterImpl userWriterImpl;

    @Test
    @DisplayName("Should Return User if username length > 8 ")
    void createUser_whenDataAreCorrect_thenShouldReturnUser() throws Exception {

        // Arrange
        String user = "longerNameThen8Character";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/users")
                                                              .contentType(MediaType.APPLICATION_JSON)
                                                              .accept(MediaType.APPLICATION_JSON)
                                                              .content(new ObjectMapper().writeValueAsString(user));
        when(userWriterImpl.save(any(User.class)))
                .thenReturn(User.create(user, UUID.randomUUID()));

        // Act
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseBodyAsString = mvcResult.getResponse().getContentAsString();
        UserJpaEntity newUser = new ObjectMapper().readValue(responseBodyAsString, UserJpaEntity.class);

        // Assert
        Assertions.assertNotNull(newUser.getUuid());
        Assertions.assertEquals(user, newUser.getUserName());
    }


    @Test
    @DisplayName("Should Return error if username length < 8 ")
    void createUser_whenDataAreInvalid_thenShouldReturnError() throws Exception {

        // Arrange
        String user = "short";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/users")
                                                              .contentType(MediaType.APPLICATION_JSON)
                                                              .accept(MediaType.APPLICATION_JSON)
                                                              .content(new ObjectMapper().writeValueAsString(user));
        when(userWriterImpl.save(any(User.class)))
                .thenReturn(User.create(user, UUID.randomUUID()));

        // Act
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        // Assert
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(),
                                mvcResult.getResponse().getStatus(),
                                "Incorrect HTTP status code returned");
    }

}