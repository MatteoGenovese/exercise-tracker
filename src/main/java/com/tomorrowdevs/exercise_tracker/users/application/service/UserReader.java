package com.tomorrowdevs.exercise_tracker.users.application.service;

import com.tomorrowdevs.exercise_tracker.users.domain.model.User;

import java.util.List;


public interface UserReader {

    public List <User> read();

}
