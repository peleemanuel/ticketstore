package com.example.ticketstore.exceptions;

public class EventAlreadyExistsException extends Throwable{
    String title;

    public EventAlreadyExistsException(String title) {
        super(String.format("An event with the username %s does not exist!", title));
        this.title = title;
    }
}
