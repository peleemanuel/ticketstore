package com.example.ticketstore.exceptions;

public class UserDoesNotExistException extends Throwable {
    String username;

    public UserDoesNotExistException(String username) {
        super(String.format("An account with the username %s does not exist!", username));
        this.username = username;
    }
}
