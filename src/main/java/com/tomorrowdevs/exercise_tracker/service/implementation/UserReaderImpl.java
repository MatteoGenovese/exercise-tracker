package com.tomorrowdevs.exercise_tracker.service.implementation;


import com.tomorrowdevs.exercise_tracker.error.DataNotFoundException;
import com.tomorrowdevs.exercise_tracker.model.UserResponse;
import com.tomorrowdevs.exercise_tracker.service.UserReader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReaderImpl implements UserReader {

    public List<UserResponse> read() {

        List <UserResponse> userList = List.of();
        if( userList.isEmpty() ){
            throw new DataNotFoundException("Data not found");
        }
        return List.of();
    }

}
