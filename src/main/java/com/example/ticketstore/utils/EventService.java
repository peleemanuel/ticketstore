package com.example.ticketstore.utils;

import com.example.ticketstore.exceptions.*;
import com.example.ticketstore.models.Event;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;

import java.util.List;

public class EventService {
    private static Nitrite db;
    private static ObjectRepository<Event> eventRepository;

    public static void loadEventsFromDatabase() {
        db = Nitrite.builder().compressed().filePath("event-database.db").openOrCreate();
        eventRepository = db.getRepository(Event.class);
    }

    public static void addEvent(String title, String artist, String data, int ticketNumbers) throws EventAlreadyExistsException, CouldNotWriteEventsException, EmptyFieldsException {

        try {
            checkEventDoesNotAlreadyExistOrIsNull(title);
            Event event = new Event(title, artist, data, ticketNumbers);
            eventRepository.insert(event);
        } catch (EmptyFieldsException | EventAlreadyExistsException e) {
            e.printStackTrace();
            throw e;
        }

    }

    public static void deleteEvent(String title) throws EventDoesNotExistsException{
        try {
            eventRepository.remove(ObjectFilters.eq("title",title));
        }
        catch (EventDoesNotExistsException e){
            e.printStackTrace();
            throw e;
        }
    }

    public static void modifyEvent(Event event){
            eventRepository.update(ObjectFilters.eq("title",event.getTitle()),event);
    }

    private static void checkEventDoesNotAlreadyExistOrIsNull(String title) throws EventAlreadyExistsException,EmptyFieldsException {
        if (title.isBlank())
            throw new EmptyFieldsException();

        Event existingEvent = eventRepository.find(ObjectFilters.eq("title", title)).firstOrDefault();

        if (existingEvent != null) {
            throw new EventAlreadyExistsException(title);
        }

    }
    public static boolean verifyEnoughSpaceAtConcert(String title, int wantedTickets){
        Event existingEvent = eventRepository.find(ObjectFilters.eq("title", title)).firstOrDefault();
        return wantedTickets <= existingEvent.getTicketNumbers();
    }

    public static void buyTickets(String title, int wantedTickets){
        Event existingEvent = eventRepository.find(ObjectFilters.eq("title", title)).firstOrDefault();
        int leftTickets = existingEvent.getTicketNumbers() - wantedTickets;
        existingEvent.setTicketNumbers(leftTickets);
        eventRepository.update(ObjectFilters.eq("title",existingEvent.getTitle()),existingEvent);
    }

    public static List<Event> getEvents() {
        // Retrieve all users from the Nitrite database
        return eventRepository.find().toList();
    }

    public static void closeDatabase() {
        db.close();
    }
}
