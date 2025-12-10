package com.tomorrowdevs.exercise_tracker.common.application.repository;

import com.tomorrowdevs.exercise_tracker.common.domain.EntityNotFound;
import com.tomorrowdevs.exercise_tracker.common.domain.entity.Entity;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.repository.jpa.StudentEntity;

import java.util.*;

import static com.tomorrowdevs.exercise_tracker.users.domain.error.StudentNotFound.uuidNotFound;

public abstract class InMemoryRepository <E extends Entity> implements EntityRepository <E> {

    Map<UUID, E> entities = new HashMap<>();

    @Override
    public List<E> read() {
        return new ArrayList<>(entities.values());
    }

    @Override
    public void save(E entity) {
        E entityFound = findByUuid(entity.uuid());

        if (entityFound != null){
            entities.replace(entity.uuid(), entity);
            return;
        }

        entities.put(
                entity.uuid(),
                entity
        );
    }

    @Override
    public E findByUuid(UUID uuid) {
        return entities.get(uuid);
    }

    @Override
    public void editByUuid(E entity){
        E entityFound = findByUuid(entity.uuid());

        if (entityFound == null){
            throw EntityNotFound.uuidNotFound(entity);
        }


//        studentEntity.setUsername(student.username().getValue());
    }

}
