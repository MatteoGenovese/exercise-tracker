package com.tomorrowdevs.exercise_tracker.users.infrastructure.controller;

import com.tomorrowdevs.exercise_tracker.users.application.service.UserEditor;
import com.tomorrowdevs.exercise_tracker.users.domain.model.User;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.controller.request.ChangeUsernameRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/users")
public class ChangeUsernameController {

    @Autowired
    UserEditor userEditor;

    @PutMapping()
    public ResponseEntity <User> changeUserUsernameV1(
            @RequestBody
            @Valid
            ChangeUsernameRequest userRequest) {

        User newUser = User.create(userRequest.getUsername().getValue());
        return ResponseEntity.ok(userEditor.edit(newUser));
    }

}
