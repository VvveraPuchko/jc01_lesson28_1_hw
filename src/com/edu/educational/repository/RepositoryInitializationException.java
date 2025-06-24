package com.edu.educational.repository;

public class RepositoryInitializationException extends RuntimeException {

    public RepositoryInitializationException(String message) {
        super(message);
    }

    public RepositoryInitializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryInitializationException(Throwable cause) {
        super(cause);
    }
}

