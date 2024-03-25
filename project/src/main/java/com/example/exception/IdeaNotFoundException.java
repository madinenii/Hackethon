package com.example.exception;

public class IdeaNotFoundException extends RuntimeException {

    public IdeaNotFoundException(String message) {
        super(message);
    }
}