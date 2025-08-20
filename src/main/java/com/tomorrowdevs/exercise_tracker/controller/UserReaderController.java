package com.tomorrowdevs.exercise_tracker.controller;

import com.tomorrowdevs.exercise_tracker.model.UserRequest;
import com.tomorrowdevs.exercise_tracker.model.UserResponse;
import com.tomorrowdevs.exercise_tracker.service.UserReader;
import com.tomorrowdevs.exercise_tracker.service.UserWritter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserReaderController {

    @Autowired
    UserReader userReader;

    @PostMapping()
    public ResponseEntity <List<UserResponse>> addNewUserV1(@RequestBody
    @Valid
    UserRequest userRequest){
        return ResponseEntity.ok(userReader.read());
    }

}
