package com.example.demo.Exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Integer id) {
        super("Could not find item" + id);
    }
}