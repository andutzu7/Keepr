package com.example.demo.Exceptions;

public class AttributeNotFoundException extends RuntimeException {

    public AttributeNotFoundException(Integer id) {
        super("Could not find item" + id);
    }
}