package com.tomorrowdevs.exercise_tracker.common.application.repository;

import com.tomorrowdevs.exercise_tracker.common.domain.entity.Entity;

import java.util.*;

public abstract class InMemoryRepository <E extends Entity> implements EntityRepository <E> {

    Map<UUID, E> entities = new HashMap<>();

    @Override
    public List<E> read() {
        return new ArrayList<>(entities.values());
    }

    @Override
    public void save(E entity) {
        entities.put(
                entity.uuid(),
                entity
        );
    }

    @Override
    public E findByUuid(UUID uuid) {
        return entities.get(uuid);
    }

}
