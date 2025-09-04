package com.tomorrowdevs.exercise_tracker.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomorrowdevs.exercise_tracker.controller.user.UserReaderController;
import com.tomorrowdevs.exercise_tracker.model.api.UserResponse;
import com.tomorrowdevs.exercise_tracker.service.UserReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(
        controllers = UserReaderController.class,
        excludeAutoConfiguration = {SecurityAutoConfiguration.class}
)
class UserReaderImplControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserReader userReader;

    @Test
    @DisplayName("Should Return Users")
    void readUserlist_whenDataAreFound_thenShouldReturnUsers() throws Exception {

        // Arrange
        UserResponse user1 = new UserResponse("testtest1", "1");
        UserResponse user2 = new UserResponse("testtest2", "2");
        List <UserResponse> userList = List.of(user1, user2);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users")
                                                              .contentType(MediaType.APPLICATION_JSON)
                                                              .accept(MediaType.APPLICATION_JSON);
        when(userReader.read()).thenReturn(userList);

        // Act
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseBodyAsString = mvcResult.getResponse().getContentAsString();
        List<UserResponse> newUser = new ObjectMapper()
                .readValue(responseBodyAsString,
                           new TypeReference <List<UserResponse>>() {});

        // Assert
        Assertions.assertFalse(newUser.isEmpty());
    }
}