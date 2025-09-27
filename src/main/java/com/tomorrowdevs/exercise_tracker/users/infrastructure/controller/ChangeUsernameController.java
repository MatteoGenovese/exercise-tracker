package com.tomorrowdevs.exercise_tracker.users.infrastructure.controller;

import com.tomorrowdevs.exercise_tracker.users.domain.model.User;
import com.tomorrowdevs.exercise_tracker.users.application.service.UserWriter;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.controller.request.ChangeUsernameRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/users")
public class ChangeUsernameController {

    @Autowired
    UserWriter userWriter;

    @PutMapping()
    public ResponseEntity <User> changeUserUsernameV1(
            @RequestBody
            @Valid
            ChangeUsernameRequest userRequest) {

        User newUser = User.create(userRequest.getUsername());
        return ResponseEntity.ok(userWriter.save(newUser));
    }

}
