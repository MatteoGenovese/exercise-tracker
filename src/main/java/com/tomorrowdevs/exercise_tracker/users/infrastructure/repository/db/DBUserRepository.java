package com.tomorrowdevs.exercise_tracker.users.infrastructure.repository.db;

import com.tomorrowdevs.exercise_tracker.users.application.repository.UserRepository;
import com.tomorrowdevs.exercise_tracker.users.domain.model.User;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.repository.jpa.UserJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
@ConditionalOnProperty(name = "app.repository.type", havingValue = "db")
public class DBUserRepository implements UserRepository {

    @Autowired
    private UserJpaRepository userJpaRepository;

    public List <User> read() {
        List <UserJpaEntity> userList = userJpaRepository.findAll();
        return toDomain(userList);
    }

    public User save(User user) {
        UserJpaEntity saved = userJpaRepository.save(UserJpaEntity.create(user.username().getValue(),
                                                                          user.uuid().toString()));
        return toDomain(saved);
    }

    private List <User> toDomain(List <UserJpaEntity> userJpaEntities) {
        return userJpaEntities
                .stream()
                .map(userResponse -> User.create(
                        UUID.fromString(userResponse.getUuid()),
                        userResponse.getUsername()
                        ))
                .toList();
    }

    private User toDomain(UserJpaEntity userJpaEntity) {
        return User.create( UUID.fromString(userJpaEntity.getUuid()), userJpaEntity.getUsername());
    }
}
