package com.tomorrowdevs.exercise_tracker.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class AppConfigTest {

    private List<Task> tasks;

    @BeforeEach
    void setUp() {
        tasks = new ArrayList<>(List.of(
                new Task("001", "Task A", "This is Task A", null),
                new Task("002", "Task B", "This is Task B", null),
                new Task("003", "Task C", "This is Task C", null),
                new Task("004", "Task D", "This is Task D", null)
        ));
        Iterator<Task> iterator = tasks.iterator();

        while (iterator.hasNext()){
            Task next = iterator.next();
        }
    }


    @Test
    void whenUsingForEachRemaining_thenIterateFromCurrentPosition() {
        ListIterator<Task> iterator = tasks.listIterator(tasks.size());
        // skip tasks before "Task B" (inclusive)

        System.out.println("Tasks before [ Task B ]:");
        while (iterator.hasPrevious()) {
            Task task = iterator.previous();
            if (task.getName().equals("Task B")) {
                break;
            }

            System.out.println(task.getName());
        }
        System.out.println("Tasks after [ Task B ]:");

        iterator.forEachRemaining(task -> {
            System.out.println(task.getName());
        });
    }

}