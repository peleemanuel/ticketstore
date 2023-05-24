package com.example.ticketstore.exceptions;

public class EmptyUsernameOrPasswordException extends Exception {
    public EmptyUsernameOrPasswordException() {
        super(String.format("Username or password cannot be empty!"));
    }
}
