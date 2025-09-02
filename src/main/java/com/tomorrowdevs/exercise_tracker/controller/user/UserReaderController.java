package com.tomorrowdevs.exercise_tracker.controller.user;

import com.tomorrowdevs.exercise_tracker.model.api.UserResponse;
import com.tomorrowdevs.exercise_tracker.service.UserReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
