package com.example.ticketstore.utils;

public class ConcertRetain {
    private static String concertName;

    public static String getConcertName() {
        return concertName;
    }

    public static void setConcertName(String concertName) {
        ConcertRetain.concertName = concertName;
        int commaIndex = concertName.indexOf(",");

        if (commaIndex != -1) {
            ConcertRetain.concertName = concertName.substring(0, commaIndex);
        } else {
            ConcertRetain.concertName = concertName;
        }
    }

}
