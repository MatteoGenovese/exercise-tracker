package com.tomorrowdevs.exercise_tracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomorrowdevs.exercise_tracker.model.UserRequest;
import com.tomorrowdevs.exercise_tracker.model.UserResponse;
import com.tomorrowdevs.exercise_tracker.service.UserWritter;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(
        controllers = UserWritterController.class,
        excludeAutoConfiguration = {SecurityAutoConfiguration.class}
        )
class UserWritterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserWritter userWritter;

    @Test
    @DisplayName("Should Return User if username length > 8 ")
    void createUser_whenDataAreCorrect_thenShouldReturnUser() throws Exception {

        String user = "longerNameThen8Character";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user));

        when(userWritter.save(any(UserRequest.class)))
                .thenReturn(new UserResponse(user));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseBodyAsString = mvcResult.getResponse().getContentAsString();
        UserResponse newUser = new ObjectMapper().readValue(responseBodyAsString, UserResponse.class);

        Assertions.assertEquals(user, newUser.getUserName());
        Assertions.assertNotNull(newUser.getUuid());

    }


    @Test
    @DisplayName("Should Return error if username length < 8 ")
    void createUser_whenDataAreInvalid_thenShouldReturnError() throws Exception {

        String user = "short";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/users")
                                                              .contentType(MediaType.APPLICATION_JSON)
                                                              .accept(MediaType.APPLICATION_JSON)
                                                              .content(new ObjectMapper().writeValueAsString(user));

        when(userWritter.save(any(UserRequest.class)))
                .thenReturn(new UserResponse(user));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(),
                                mvcResult.getResponse().getStatus(),
                                "Incorrect HTTP status code returned");

    }

}