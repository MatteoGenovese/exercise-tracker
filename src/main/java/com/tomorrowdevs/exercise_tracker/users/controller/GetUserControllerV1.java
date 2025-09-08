package com.tomorrowdevs.exercise_tracker.users.controller;

import com.tomorrowdevs.exercise_tracker.users.model.api.UserResponse;
import com.tomorrowdevs.exercise_tracker.users.service.UserReader;
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
    UserReader userReader;

    @GetMapping
    public ResponseEntity <List<UserResponse>> getUserListV1(){
        return ResponseEntity.ok(userReader.read());
    }

}
