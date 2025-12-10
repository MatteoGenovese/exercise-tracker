package com.tomorrowdevs.exercise_tracker.common.domain;

import com.tomorrowdevs.exercise_tracker.users.domain.error.InvalidData;

public class EntityNotFound extends InvalidData {

    public EntityNotFound(String message) {
        super(message);
    }

    public static EntityNotFound uuidNotFound( Object id){
        return new EntityNotFound(id.getClass().getSimpleName()+" not found");
    }


}
