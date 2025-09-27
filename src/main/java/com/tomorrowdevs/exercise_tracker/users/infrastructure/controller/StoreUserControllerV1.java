package com.tomorrowdevs.exercise_tracker.users.infrastructure.controller;

import com.tomorrowdevs.exercise_tracker.users.infrastructure.controller.request.UserRequest;
import com.tomorrowdevs.exercise_tracker.users.domain.model.User;
import com.tomorrowdevs.exercise_tracker.users.application.service.UserWriter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/users")
public class StoreUserControllerV1 {

    @Autowired
    UserWriter userWriter;

    @PostMapping()
    public ResponseEntity <User> storeNewUserV1(
            @RequestBody
            @Valid
            UserRequest userRequest) {

        User newUser = User.create(userRequest.getUsername().getValue());
        return ResponseEntity.ok(userWriter.save(newUser));
    }

}
