package com.tomorrowdevs.exercise_tracker.configuration;

public class Task implements Comparable<Task> {


    String id;
    String name;
    String description;
    Task nextask;

    public Task(String id, String name, String description, Task nextask) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.nextask = nextask;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Task getNextask() {
        return nextask;
    }

    // ...
    @Override
    public int compareTo(Task otherTask) {
        return this.getName().compareTo(otherTask.getName());
    }

}
