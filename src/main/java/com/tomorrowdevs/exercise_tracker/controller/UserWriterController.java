package com.tomorrowdevs.exercise_tracker.controller;

import com.tomorrowdevs.exercise_tracker.model.UserResponse;
import com.tomorrowdevs.exercise_tracker.model.UserRequest;
import com.tomorrowdevs.exercise_tracker.service.UserWriter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserWriterController {

    @Autowired
    UserWriter userWriter;

    @PostMapping()
    public ResponseEntity<UserResponse> addNewUserV1(@RequestBody @Valid
    UserRequest userRequest){
        return ResponseEntity.ok(userWriter.save(userRequest));
    }

}
