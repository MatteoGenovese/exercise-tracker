package com.tomorrowdevs.exercise_tracker.users.infrastructure.error.model;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


public class ApiError {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String path;
    private String localizedMessage;

    public ApiError(HttpStatus status, String error, String path, String localizedMessage) {
        this.timestamp = LocalDateTime.now();
        this.status = status.value();
        this.error = error;
        this.path = path;
        this.localizedMessage = localizedMessage;
    }


    //jackson requested constructor
    public ApiError() {
    }

    public ApiError(LocalDateTime timestamp, int status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }

    //jackson requested setters
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getParent() {
        return localizedMessage;
    }

    public void setParent(String localizedMessage) {
        this.localizedMessage = localizedMessage;
    }
}
