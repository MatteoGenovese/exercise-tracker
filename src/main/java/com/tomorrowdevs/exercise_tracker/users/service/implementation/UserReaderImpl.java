package com.tomorrowdevs.exercise_tracker.users.service.implementation;


import com.tomorrowdevs.exercise_tracker.users.error.DataNotFoundException;
import com.tomorrowdevs.exercise_tracker.users.model.api.UserResponse;
import com.tomorrowdevs.exercise_tracker.users.repository.UserRepository;
import com.tomorrowdevs.exercise_tracker.users.service.UserReader;
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
