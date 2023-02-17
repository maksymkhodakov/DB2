package com.lab.lab2.exceptions;

public class CrewMemberNotFound extends RuntimeException {
    public CrewMemberNotFound(String message) {
        super(message);
    }

    public CrewMemberNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
