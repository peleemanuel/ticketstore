package com.example.ticketstore.utils;

import com.example.ticketstore.exceptions.*;
import com.example.ticketstore.models.Event;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectFilter;
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

    public static void addEvent(String title, String artist, String data, int ticketNumbers) throws EventAlreadyExistsException, CouldNotWriteEventsException {

        try {
            checkEventDoesNotAlreadyExistOrIsNull(title);
            Event event = new Event(title, artist, data, ticketNumbers);
            eventRepository.insert(event);
        } catch (EmptyFieldsException | EventAlreadyExistsException e) {
            e.printStackTrace();
        }

    }

    public static void deleteEvent(String title) throws EventDoesNotExistsException{
        try {
            eventRepository.remove(ObjectFilters.eq("title",title));
        }
        catch (EventDoesNotExistsException e){
            e.printStackTrace();
        }
    }

    private static void checkEventDoesNotAlreadyExistOrIsNull(String title) throws EventAlreadyExistsException,EmptyFieldsException {
        if (title.isBlank())
            throw new EmptyFieldsException();

        Event existingEvent = eventRepository.find(ObjectFilters.eq("title", title)).firstOrDefault();

        if (existingEvent != null) {
            throw new EventAlreadyExistsException(title);
        }

    }

    public static boolean checkEventExists(String title) throws UserDoesNotExistException {
        // if the user is found then return True
        Event existingEvent = eventRepository.find(ObjectFilters.eq("title", title)).firstOrDefault();

        return existingEvent != null;
    }

    public static Event getEvent(String title) {
        Event existingEvent = eventRepository.find(ObjectFilters.eq("title", title)).firstOrDefault();

        return existingEvent;
    }

    public static List<Event> getEvents() {
        // Retrieve all users from the Nitrite database
        return eventRepository.find().toList();
    }

    public static void closeDatabase() {
        db.close();
    }
}
