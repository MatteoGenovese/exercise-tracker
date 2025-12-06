package com.tomorrowdevs.exercise_tracker.users.application.repository;

import com.tomorrowdevs.exercise_tracker.users.domain.model.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@ConditionalOnProperty(name = "app.repository.type", havingValue = "test")
public class InMemoryUserRepository implements UserRepository {

    Map<UUID, User> userMap = new HashMap<>();

    @Override
    public List<User> read() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public void save(User user) {
        userMap.put(
                user.uuid(),
                user
        );
    }

    @Override
    public User findUserByUuid(UUID uuid) {
        return userMap.get(uuid);
    }

    @Override
    public User editUserByUuid(User user) {
        return null;
    }

}
