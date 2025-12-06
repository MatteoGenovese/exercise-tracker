package com.tomorrowdevs.exercise_tracker.users.infrastructure.repository.db;

import com.tomorrowdevs.exercise_tracker.users.application.repository.UserRepository;
import com.tomorrowdevs.exercise_tracker.users.domain.error.UserNotFound;
import com.tomorrowdevs.exercise_tracker.users.domain.model.User;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.repository.jpa.UserEntity;
import jakarta.transaction.Transactional;
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

    public List<User> read() {
        List<UserEntity> userList = userJpaRepository.findAll();
        return toDomain(userList);
    }

    public void save(User user) {
        userJpaRepository.save(UserEntity.create(
                user.username().getValue(),
                user.uuid()
        ));
    }

    @Override
    public User findUserByUuid(UUID uuid) {
        UserEntity memorized = userJpaRepository.findByUuid(uuid).orElseThrow(UserNotFound::uuidNotFound);
        return toDomain(memorized);
    }

    @Transactional
    @Override
    public User editUserByUuid(User user) {
        UserEntity userEntity = userJpaRepository.findByUuid(user.uuid()).orElseThrow(UserNotFound::uuidNotFound);

        userEntity.setUsername(user.username().getValue());
        UserEntity saved = userJpaRepository.save(userEntity);
        return toDomain(saved);
    }

    private List<User> toDomain(List<UserEntity> userJpaEntities) {
        return userJpaEntities.stream().map(userResponse -> User.create(
                userResponse.getUuid(),
                userResponse.getUsername()
        )).toList();
    }

    private User toDomain(UserEntity userEntity) {
        return User.create(
                userEntity.getUuid(),
                userEntity.getUsername()
        );
    }
}
