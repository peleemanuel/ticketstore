package com.example.ticketstore.utils;

import com.example.ticketstore.exceptions.EmptyFieldsException;
import com.example.ticketstore.exceptions.EventAlreadyExistsException;
import com.example.ticketstore.exceptions.EventDoesNotExistsException;
import com.example.ticketstore.models.Event;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EventServiceTest {

    @BeforeEach
    void setUp(){
        EventService.loadEventsFromDatabase();
    }

    @AfterEach
    public void tearDownClass() {
        EventService.closeDatabase();
    }
    @Order(1)
    @Test
     void testSuccessfulAddEvent(){
        assertDoesNotThrow(()->EventService.addEvent("dummy", "dummy", "dummy", 100));
    }
    @Order(2)

    @Test
    void testAddEventExistsException(){
        assertThrows(EventAlreadyExistsException.class, ()-> EventService.addEvent("dummy", "dummy", "dummy", 100));
    }
    @Order(3)

    @Test
    void testSuccessfulModifyEvent(){
        Event event = new Event("dummy", "dummies", "test", 150);
        assertDoesNotThrow(() -> EventService.modifyEvent(event));

    }
    @Order(4)
    @Test
    void testVerifyEnoughSpaceAtConcert(){
        assertTrue(EventService.verifyEnoughSpaceAtConcert("dummy",50));
    }
    @Order(5)
    @Test
    void testVerifyNotEnoughSpaceAtConcert(){
        assertFalse(EventService.verifyEnoughSpaceAtConcert("dummy",151));
    }
    @Order(6)
    @Test
    void testSuccessfulBuyTickets(){
        EventService.buyTickets("dummy",5);
        assertTrue(EventService.verifyEnoughSpaceAtConcert("dummy",145));
        assertFalse(EventService.verifyEnoughSpaceAtConcert("dummy",146));
        EventService.closeDatabase();
    }
    @Order(7)
    @Test
    void testGetEvents() {
        assertNotNull(EventService.getEvents());
    }
    @Order(8)
    @Test
    void testSuccessfulDeleteEvent(){
        assertDoesNotThrow(()-> EventService.deleteEvent("dummy"));
    }

}
