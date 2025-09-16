package com.tomorrowdevs.exercise_tracker.users.service;

import com.tomorrowdevs.exercise_tracker.users.model.domain.User;

import java.util.List;


public interface UserReader {

    List <User> read();

}
