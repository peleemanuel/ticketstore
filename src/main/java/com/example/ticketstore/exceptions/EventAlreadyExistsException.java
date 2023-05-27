package com.example.ticketstore.exceptions;

public class EventAlreadyExistsException extends RuntimeException{
    String title;

    public EventAlreadyExistsException(String title) {
        super(String.format("An event with the title %s does not exist!", title));
        this.title = title;
    }
}
