package com.app.todoapp.exception.models;

public class IdNotFoundException extends Exception {
    public IdNotFoundException() {
    }

    public IdNotFoundException(String message) {
        super(message);
    }
}
