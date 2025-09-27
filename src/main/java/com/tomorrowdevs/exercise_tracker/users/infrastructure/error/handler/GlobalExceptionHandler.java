package com.tomorrowdevs.exercise_tracker.users.infrastructure.error.handler;

import com.tomorrowdevs.exercise_tracker.users.domain.error.InvalidData;
import com.tomorrowdevs.exercise_tracker.users.domain.error.InvalidUsername;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.error.*;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.error.model.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(InvalidUsername.class)
    public ResponseEntity <ApiError> handleInvalidUsername(InvalidUsername ex,
            HttpServletRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                request.getRequestURI(),
                ex.getLocalizedMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(InvalidData.class)
    public ResponseEntity <ApiError> handleInvalidData(InvalidData ex, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                request.getRequestURI(),
                ex.getLocalizedMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(DataNotFoundError.class)
    public ResponseEntity <ApiError> handleDataNotFound(DataNotFoundError ex,
            HttpServletRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                request.getRequestURI(),
                ex.getLocalizedMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(DirectoryNotFoundError.class)
    public ResponseEntity <ApiError> handleDirectoryNotCreatedException(DirectoryNotFoundError ex,
            HttpServletRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                request.getRequestURI(),
                ex.getLocalizedMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(FileNotFoundError.class)
    public ResponseEntity <ApiError> handleFileNotCreatedException(FileNotFoundError ex,
            HttpServletRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                request.getRequestURI(),
                ex.getLocalizedMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(UnableToExtractDataError.class)
    public ResponseEntity <ApiError> handleUnableToExtractDataException(UnableToExtractDataError ex,
            HttpServletRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                request.getRequestURI(),
                ex.getLocalizedMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(UnableToWriteOnFileError.class)
    public ResponseEntity <ApiError> handleUnableToWriteOnFileException(UnableToWriteOnFileError ex,
            HttpServletRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                request.getRequestURI(),
                ex.getLocalizedMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(UnableToCreateDirectoryError.class)
    public ResponseEntity <ApiError> handleUnableToCreateDirectoryException(
            UnableToCreateDirectoryError ex,
            HttpServletRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                request.getRequestURI(),
                ex.getLocalizedMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }
}
