package com.example.demo.Exceptions;

public class AttributeValueNotFoundException extends RuntimeException {

    public AttributeValueNotFoundException(Integer id) {
        super("Could not find item" + id);
    }
}