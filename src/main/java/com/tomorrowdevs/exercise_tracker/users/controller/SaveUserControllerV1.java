package com.tomorrowdevs.exercise_tracker.users.controller;

import com.tomorrowdevs.exercise_tracker.users.model.api.UserRequest;
import com.tomorrowdevs.exercise_tracker.users.model.api.UserResponse;
import com.tomorrowdevs.exercise_tracker.users.model.domain.User;
import com.tomorrowdevs.exercise_tracker.users.service.UserSaver;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class SaveUserControllerV1 {

    @Autowired
    UserSaver userWriter;

    @PostMapping()
    public ResponseEntity<UserResponse> addNewUserV1(@RequestBody @Valid
    UserRequest userRequest){

        User newUser = User.create(userRequest.getUsername());
        return ResponseEntity.ok(userWriter.save(newUser));
    }

}
