package com.example.ticketstore.models;

import org.dizitart.no2.objects.Id;

public class Event {
    @Id
    private String title;

    private String artist;

    private String data;

    private int ticketNumbers;

    public Event() {
    }

    public Event(String title, String artist, String data, int ticketNumbers) {
        this.title = title;
        this.artist = artist;
        this.data = data;
        this.ticketNumbers = ticketNumbers;
    }

    public int getTicketNumbers() {
        return ticketNumbers;
    }

    public String getData() {
        return data;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }
}
