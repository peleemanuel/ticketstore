package com.example.ticketstore.exceptions;

public class CouldNotWriteEventsException extends RuntimeException {
    public String title;

    public CouldNotWriteEventsException(String title) {
        super(String.format("Couldn't write event %s", title));
        this.title = title;
    }
}

