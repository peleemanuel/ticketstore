package com.example.ticketstore.exceptions;

public class EmptyFieldsException extends Exception {
    public EmptyFieldsException() {
        super(String.format("Username or password cannot be empty!"));
    }
}
