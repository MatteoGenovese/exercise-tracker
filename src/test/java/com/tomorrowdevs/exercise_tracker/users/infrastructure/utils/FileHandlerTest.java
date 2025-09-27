package com.tomorrowdevs.exercise_tracker.users.infrastructure.utils;

import com.tomorrowdevs.exercise_tracker.users.domain.model.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FileHandlerTest {

    @Autowired
    private FileHandler fileHandler;

    @Autowired
    private PathResolver pathResolver;

    @AfterAll
    void deleteFolder() throws IOException {
        Files.delete(pathResolver.getFilePath());
        Files.delete(pathResolver.getDirectoryPath());
        Files.delete(pathResolver.getDirectoryPath().getParent());
    }



    @Test
    @DisplayName("Should Create Folder")
    @Order(1)
    void createFolder_whenMissing_thenCreatesIt() {
        // Arrange
        Path dirPath = pathResolver.getDirectoryPath();

        // Act
        fileHandler.createFolder(dirPath);

        // Assert
        assertThat(Files.exists(pathResolver.getDirectoryPath())).isTrue();
    }

    @Test
    @DisplayName("Should Create the blank File")
    @Order(2)
    void createFile_whenMissing_thenCreatesIt() throws IOException {
        // Arrange
        String expected = "[ ]\n";
        Path dirPath = pathResolver.getDirectoryPath();
        Path filePath = pathResolver.getFilePath();

        // Act
        fileHandler.createFolder(dirPath);
        fileHandler.createBlankFile(filePath);

        // Assert
        assertThat(Files.exists(dirPath)).isTrue();
        assertEquals(expected, Files.readString(filePath).replace("\r\n", "\n"));
    }


    @Test
    @DisplayName("Should Save a new User")
    @Order(3)
    void saveUser_whenUserDetailsProvided_thenShouldWriteUserInTheFile() {
        // Arrange
        User user = User.create("username");

        // Act
        User response = fileHandler.save(user);

        // Assert
        assertEquals(response.username().getValue(), user.username().getValue());
        assertEquals(response.uuid().toString(), user.uuid().toString());
    }

    @Test
    @DisplayName("Should read all the users")
    @Order(4)
    void readUsers_whenUsersAreCollected_thenShouldReturnUserList() {
        // Act
        List <User> responseList = fileHandler.read();

        // Assert
        Assertions.assertEquals("username", responseList.getFirst().username().getValue());
    }
}