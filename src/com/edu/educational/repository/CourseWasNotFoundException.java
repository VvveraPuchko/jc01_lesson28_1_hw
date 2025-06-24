package com.edu.educational.repository;

public class CourseWasNotFoundException extends Exception {
    public CourseWasNotFoundException(String message) {
        super(message);
    }

    public CourseWasNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
