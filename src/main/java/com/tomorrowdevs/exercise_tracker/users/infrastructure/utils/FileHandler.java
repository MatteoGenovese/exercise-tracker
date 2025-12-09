package com.tomorrowdevs.exercise_tracker.users.infrastructure.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Student;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.error.FileNotFoundError;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.error.UnableToCreateDirectoryError;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.error.UnableToExtractDataError;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.error.UnableToWriteOnFileError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


@Component
public class FileHandler {

    public static final String BLANK_FILE_CONTENT = """
            [ ]
            """;
    public static final String STUDENT_BLANK = """
            \t{
            \t\t"username" : {
            \t\t\t\"value\": \"%s\"
            \t\t},
            \t\t"uuid" : "%s" 
            \t}""";
    PathResolver pathResolver;
    ObjectMapper objectMapper;

    @Autowired
    public FileHandler(PathResolver pathResolver, ObjectMapper objectMapper) {
        this.pathResolver = pathResolver;
        this.objectMapper = objectMapper;
    }

    public Student save(Student student) {
        createFolder(pathResolver.getDirectoryPath());
        createBlankFile(pathResolver.getFilePath());
        addNewStudentOnFile(pathResolver.getFilePath(),
                student
        );
        return Student.create(student.uuid(), student.username().getValue());
    }

    public List <Student> read() {
        return extractStudentsFromData();
    }

    private List <Student> extractStudentsFromData() {

        try {
            return objectMapper.readValue(new File(pathResolver.getFilePath().toUri()),
                                          new TypeReference <List <Student>>() {
                                          });
        } catch( IOException e ) {
            throw new UnableToExtractDataError("Unable to extract data", e.fillInStackTrace());
        }
    }

    public void createFolder(Path dirPath) {

        if( fileOrDirectoryNotExists(dirPath) ) {
            createFolderOrLaunchException(dirPath);
        }
    }

    public void createBlankFile(Path filePath) {

        if( fileOrDirectoryNotExists(filePath) ) {
            createFileOrLaunchException(filePath, BLANK_FILE_CONTENT);
        }
    }

    public void addNewStudentOnFile(Path path, Student student) {

        try {
            String fileContent = Files.readString(path);
            String newStudent = STUDENT_BLANK.formatted(student.username(), student.uuid().toString());
            int endFileIndex = fileContent.lastIndexOf("]") - 1;
            boolean alreadyHasStudent = fileContent.contains("username");
            String prefix = alreadyHasStudent ? ",\n" : "\n";
            String newContent = fileContent.substring(0, endFileIndex)
                                + prefix + newStudent + "\n]";
            Files.writeString(path, newContent);
            System.out.println(newContent);
        } catch( IOException e ) {
            throw new UnableToWriteOnFileError(e.getMessage(), e.fillInStackTrace());
        }
    }

    private void writeOnBlankFile(String string) {

        try {
            Files.writeString(pathResolver.getFilePath(), string);
        } catch( IOException e ) {
            throw new UnableToWriteOnFileError(e.getMessage(), e.fillInStackTrace());
        }
    }

    public static void createFolderOrLaunchException(Path dirPath) {

        try {
            Files.createDirectories(dirPath);
        } catch( IOException e ) {
            throw new UnableToCreateDirectoryError(e.getMessage(), e.fillInStackTrace());
        }
    }

    public void createFileOrLaunchException(Path filePath, String content) {

        try {
            Files.createFile(filePath);
            writeOnBlankFile(content);
        } catch( IOException e ) {
            throw new FileNotFoundError(e.getMessage(), e.fillInStackTrace());
        }
    }

    public static boolean fileOrDirectoryNotExists(Path dirPath) {
        return !Files.exists(dirPath);
    }

}
