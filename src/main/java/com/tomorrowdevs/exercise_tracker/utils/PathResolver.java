package com.tomorrowdevs.exercise_tracker.utils;

import com.tomorrowdevs.exercise_tracker.repository.implementation.file.StorageProps;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@EnableConfigurationProperties(StorageProps.class)
public class PathResolver {

    private final Path directoryPath;
    private final String filename;

    public PathResolver(StorageProps props) {

        this.directoryPath = Paths.get(props.directory());
        this.filename = props.filename();
    }

    public Path getFilePath(){

        return directoryPath.resolve(filename);
    }

    public Path getDirectoryPath(){
        return directoryPath;
    }

}
