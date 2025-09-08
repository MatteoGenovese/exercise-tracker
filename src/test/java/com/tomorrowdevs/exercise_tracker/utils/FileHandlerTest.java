package com.tomorrowdevs.exercise_tracker.utils;

import com.tomorrowdevs.exercise_tracker.users.model.api.UserResponse;
import com.tomorrowdevs.exercise_tracker.users.model.domain.User;
import com.tomorrowdevs.exercise_tracker.users.utils.FileHandler;
import com.tomorrowdevs.exercise_tracker.users.utils.PathResolver;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FileHandlerTest {

    @TempDir
    static Path tempDir;

    @Autowired
    private FileHandler fileHandler;

    @Autowired
    private PathResolver pathResolver;

    @DynamicPropertySource
    static void props(DynamicPropertyRegistry registry) {

        registry.add("app.files.directory", () -> tempDir.resolve("data/files").toString());
        registry.add("app.files.filename", () -> "data.txt");
    }


    @Test
    @DisplayName("Should Create Folder")
    @Order(1)
    void readUsers_whenFileDoesNotExist_shouldReturnNull() {

        // Act
        List <UserResponse> read = fileHandler.read();

        // Assert
        Assertions.assertNull(read);
    }

    @Test
    @DisplayName("Should Create Folder")
    @Order(2)
    void createFolder_whenMissing_thenCreatesIt() {


        // Arrange
        Path dirPath = pathResolver.getDirectoryPath();

        // Act
        fileHandler.createFolder(dirPath);

        // Assert
        assertThat(Files.exists(dirPath)).isTrue();
    }

    @Test
    @DisplayName("Should Create the blank File")
    @Order(3)
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
    @Order(4)
    void saveUser_whenUserDetailsProvided_thenShouldWriteUserInTheFile(){

        // Arrange
        User user = User.create("username");

        // Act
        UserResponse response = fileHandler.save(user);

        // Assert
        assertEquals(response.getUserName(), user.username());
        assertEquals(response.getUuid(), user.uuid().toString());
    }

    @Test
    @DisplayName("Should read all the users")
    void readUsers_whenUsersAreCollected_thenShouldReturnUserList(){

        List <UserResponse> responseList = fileHandler.read();

        // Assert
        Assertions.assertEquals("username", responseList.getFirst().getUserName());
    }
}