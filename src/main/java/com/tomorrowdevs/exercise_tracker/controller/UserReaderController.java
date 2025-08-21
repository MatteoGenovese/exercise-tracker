package com.tomorrowdevs.exercise_tracker.controller;

import com.tomorrowdevs.exercise_tracker.model.UserResponse;
import com.tomorrowdevs.exercise_tracker.service.UserReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserReaderController {

    @Autowired
    UserReader userReader;

    @GetMapping
    public ResponseEntity <List<UserResponse>> addNewUserV1(){
        return ResponseEntity.ok(userReader.read());
    }

}
