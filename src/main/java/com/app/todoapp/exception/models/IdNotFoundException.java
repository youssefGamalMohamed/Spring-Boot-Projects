package com.app.todoapp.exception.models;

import java.util.NoSuchElementException;

public class IdNotFoundException extends NoSuchElementException {
    public IdNotFoundException() {
    }

    public IdNotFoundException(String message) {
        super(message);
    }
}
