package com.tomorrowdevs.exercise_tracker.users.application.repository;

import com.tomorrowdevs.exercise_tracker.users.domain.model.Student;

import java.util.*;

public class InMemoryStudentRepository implements StudentRepository {

    Map<UUID, Student> userMap = new HashMap<>();

    @Override
    public List<Student> read() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public void save(Student student) {
        userMap.put(
                student.uuid(),
                student
        );
    }

    @Override
    public Student findUserByUuid(UUID uuid) {
        return userMap.get(uuid);
    }

    @Override
    public Student editUserByUuid(Student student) {
        return null;
    }

}
