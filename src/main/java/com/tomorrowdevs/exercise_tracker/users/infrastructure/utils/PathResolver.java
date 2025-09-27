package com.tomorrowdevs.exercise_tracker.users.infrastructure.utils;

import com.tomorrowdevs.exercise_tracker.users.infrastructure.props.ApplicationProps;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;


@Component
@EnableConfigurationProperties(ApplicationProps.class)
public class PathResolver {

    private final Path directoryPath;
    private final String filename;

    public PathResolver(ApplicationProps props) {
        this.directoryPath = Paths.get(props.filesDirectory());
        this.filename = props.filesFilename();
    }

    public Path getFilePath() {
        return directoryPath.resolve(filename);
    }

    public Path getDirectoryPath() {
        return directoryPath;
    }

}
