package com.tomorrowdevs.exercise_tracker.users.infrastructure.props;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "app")
public record ApplicationProps(
        String filesDirectory,
        String filesFilename
) {

}
