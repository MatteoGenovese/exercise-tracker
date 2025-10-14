package com.tomorrowdevs.exercise_tracker.users.domain.error;

public class UsernameNotFound extends InvalidData{

    public UsernameNotFound(String message) {
        super(message);
    }

   public static UsernameNotFound uuidNotFound(){
        return new UsernameNotFound("uuid not found");
   }

}
