package com.tomorrowdevs.exercise_tracker.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories(
        basePackages = {
                "com.tomorrowdevs.exercise_tracker.exercises.infrastructure.repository",
                "com.tomorrowdevs.exercise_tracker.users.infrastructure.repository",
        }
)
public class AppConfig {
    // ...
}
