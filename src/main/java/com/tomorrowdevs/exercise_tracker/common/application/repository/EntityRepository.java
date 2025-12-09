package com.tomorrowdevs.exercise_tracker.common.application.repository;

import com.tomorrowdevs.exercise_tracker.common.domain.entity.Entity;

import java.util.List;
import java.util.UUID;

public interface EntityRepository <E extends Entity> {

    List<E> read();

    void save(E entity);

    E findByUuid(UUID uuid);

   //delete

}
