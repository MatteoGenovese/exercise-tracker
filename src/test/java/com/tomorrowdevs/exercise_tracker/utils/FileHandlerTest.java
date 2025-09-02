package com.tomorrowdevs.exercise_tracker.utils;

import com.tomorrowdevs.exercise_tracker.model.persistence.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class FileHandlerTest {

    @TempDir
    static Path tempDir;

    @DynamicPropertySource
    static void props(DynamicPropertyRegistry registry) {

        registry.add("app.files.directory", () -> tempDir.resolve("data/files").toString());
        registry.add("app.files.filename", () -> tempDir.resolve("data/files/data.txt").toString());
    }

    @Autowired
    private FileHandler fileHandler;

    @Test
    @DisplayName("Should Create Folder")
    void createFolder_whenMissing_thenCreatesIt() {


        // Arrange
        Path dirPath = tempDir.resolve("data/files");

        // Act
        fileHandler.createFolder(dirPath);

        // Assert
        assertThat(Files.exists(dirPath)).isTrue();
    }

    @Test
    @DisplayName("Should Create the blank File")
    void createFile_whenMissing_thenCreatesIt() throws IOException {

        // Arrange
        String expected = """
                    [ ]
                    """;
        Path dirPath = tempDir.resolve("data/files");
        Path filePath = tempDir.resolve("data/files/data.txt");

        // Act
        fileHandler.createFolder(dirPath);
        fileHandler.createBlankFile(filePath);

        // Assert
        assertThat(Files.exists(dirPath)).isTrue();
        assertThat(Files.readString(filePath).equals(expected)).isTrue();
    }

    @Test
    @DisplayName("Should write on file")
    void writeOnFile_whenItIsCreated_thenWritesOnIt() throws IOException {

        // Arrange
        UserEntity userEntity = new UserEntity("username", "id0000000");
        Path dirPath = tempDir.resolve("data/files");
        Path filePath = tempDir.resolve("data/files/data.txt");

        // Act
        fileHandler.createFolder(dirPath);
        fileHandler.createBlankFile(filePath);
        fileHandler.modifyFile(filePath, userEntity);

        // Assert
        assertThat(Files.exists(tempDir.resolve("data/files/data.txt"))).isTrue();
    }

    @Test
    @DisplayName("Should Save a new User")
    void saveUser_whenUserDetailsProvided_thenShouldWriteUserInTheFile(){

        
    }
}