package com.example.ticketstore.exceptions;

public class EmptyFieldsException extends Exception {
    public EmptyFieldsException() {
        super(String.format("Fields cannot be empty!"));
    }
}
