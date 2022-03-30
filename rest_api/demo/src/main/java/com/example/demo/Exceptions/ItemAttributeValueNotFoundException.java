package com.example.demo.Exceptions;

public class ItemAttributeValueNotFoundException extends RuntimeException {

    public ItemAttributeValueNotFoundException(Integer id) {
        super("Could not find item" + id);
    }
}