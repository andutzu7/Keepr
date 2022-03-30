package com.example.demo.Exceptions;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(Integer id) {
        super("Could not find item" + id);
    }
}