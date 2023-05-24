package com.example.ticketstore.exceptions;

public class CouldNotWriteUsersException extends RuntimeException {
    public String username;

    public CouldNotWriteUsersException(String username) {
        super(String.format("Couldn't write user %s", username));
        this.username = username;
    }
}

