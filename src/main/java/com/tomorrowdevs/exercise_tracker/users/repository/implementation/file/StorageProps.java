package com.tomorrowdevs.exercise_tracker.users.repository.implementation.file;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.files")
public record StorageProps(String directory, String filename ) {

}
