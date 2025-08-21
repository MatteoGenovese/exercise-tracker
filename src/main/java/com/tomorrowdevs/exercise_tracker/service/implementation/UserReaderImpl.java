package com.tomorrowdevs.exercise_tracker.service.implementation;


import com.tomorrowdevs.exercise_tracker.error.DataNotFoundException;
import com.tomorrowdevs.exercise_tracker.model.UserResponse;
import com.tomorrowdevs.exercise_tracker.repository.UserRepository;
import com.tomorrowdevs.exercise_tracker.service.UserReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReaderImpl implements UserReader {

   @Autowired
   private UserRepository userRepository;

    public List<UserResponse> read() {
        List <UserResponse> userList = userRepository.read();
        ifListIsEmptyThrowError(userList);
        return userList;
    }

    private void ifListIsEmptyThrowError(List <UserResponse> userList) {
        if( userList.isEmpty() ){
            throw new DataNotFoundException("Data not found");
        }
    }

}
