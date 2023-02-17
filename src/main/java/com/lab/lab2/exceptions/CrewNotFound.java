package com.lab.lab2.exceptions;

public class CrewNotFound extends RuntimeException {
    public CrewNotFound(String message) {
        super(message);
    }

    public CrewNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
