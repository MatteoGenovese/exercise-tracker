package com.tomorrowdevs.exercise_tracker.users.infrastructure.controller;

import com.tomorrowdevs.exercise_tracker.users.domain.model.User;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.controller.response.UserResponse;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.service.UserReaderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/v1/users")
public class GetUserControllerV1 {

    @Autowired
    UserReaderImpl userReaderImpl;

    @GetMapping
    public ResponseEntity <List <UserResponse>> getUserListV1() {
        return ResponseEntity.ok(mapResponse(userReaderImpl.read()));
    }

    private List <UserResponse> mapResponse(List <User> userList) {
        return userList.stream()
                       .map(user -> UserResponse.create(
                               user.username(),
                               user.uuid().toString()))
                       .toList();
    }

}
