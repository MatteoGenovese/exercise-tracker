package com.tomorrowdevs.exercise_tracker.utils;

import com.tomorrowdevs.exercise_tracker.model.api.UserResponse;
import com.tomorrowdevs.exercise_tracker.model.persistence.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class FileHandler {

    public static final String BLANK_FILE_CONTENT = """
            [ ]
            """;
    public static final String USER_BLANK = """
            \t{
            \t\tid: "%s", 
            \t\tusername: "%s"
            \t}""";
    PathResolver pathResolver;

    @Autowired
    public FileHandler(PathResolver pathResolver){
            this.pathResolver= pathResolver;
    }

    public UserResponse save(UserEntity userEntity) {
        createFolder(pathResolver.getDirectoryPath());
        createBlankFile(pathResolver.getFilePath());
        modifyFile(pathResolver.getFilePath(), userEntity);
        return new UserResponse(userEntity.getUserName(), userEntity.getUuid());
    }

    public List <UserResponse> read() {
        return null;
    }

    public void createFolder(Path dirPath) {

        if( fileOrDirectoryNotExists(dirPath)){
            createFolderOrLaunchException(dirPath);
        }
    }

    public void createBlankFile(Path filePath) {

        if( fileOrDirectoryNotExists(filePath)){
            createFileOrLaunchException(filePath, BLANK_FILE_CONTENT);
        }
    }

    public void modifyFile(Path path, UserEntity user) {

        try {
            String fileContent = Files.readString(path).replace("\r\n", "\n");
            String newUser = USER_BLANK.formatted(user.getUuid(), user.getUserName());
            int endFileIndex = fileContent.lastIndexOf("]")-1;
            boolean alreadyHasUser = fileContent.contains("username");
            String prefix = alreadyHasUser? ",\n" : "\n";
            String newContent = fileContent.substring(0, endFileIndex)
                                                   +prefix +newUser +"\n]";
            Files.writeString(path, newContent);
            System.out.println(newContent);
        } catch( IOException e ) {
            throw new RuntimeException(e);
        }
    }

    private void writeOnBlankFile(String string) {

        try {
            Files.writeString(pathResolver.getFilePath(), string);
        } catch( IOException e ) {
            throw new RuntimeException(e);
        }
    }

    public static void createFolderOrLaunchException(Path dirPath){

        try {
            Files.createDirectories(dirPath);
        } catch( IOException e ) {
            throw new RuntimeException(e);
        }
    }

    public void createFileOrLaunchException(Path filePath, String content){

        try {
            Files.createFile(filePath);
            writeOnBlankFile(content);
        } catch( IOException e ) {
            throw new RuntimeException(e);
        }
    }

    public static boolean fileOrDirectoryNotExists(Path dirPath){
        return !Files.exists(dirPath);
    }

}
