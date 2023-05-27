package com.example.ticketstore.utils;

import com.example.ticketstore.exceptions.EmptyUsernameOrPasswordException;
import com.example.ticketstore.exceptions.UserDoesNotExistException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    @BeforeEach
    void setUp() {
        UserService.loadUsersFromDatabase();
    }

    @Test
    @DisplayName("Add user without username")
    void testAddUserNoUsername() throws EmptyUsernameOrPasswordException {
        assertThrows(EmptyUsernameOrPasswordException.class, () -> UserService.addUser("", "123", "Client"));
    }

    @Test
    @DisplayName("Add user without password")
    void testAddUserNoPassword() throws EmptyUsernameOrPasswordException {
        assertThrows(EmptyUsernameOrPasswordException.class, () -> UserService.addUser("User de test", "", "Client"));
    }

    @Test
    @DisplayName("SUccessfully add an user")
    void testAddUserSuccessfully() {
        assertDoesNotThrow(() -> UserService.addUser("User102", "Parola123", "Client"));
    }

    @Test
    @DisplayName("Check if an user has already created an account")
    void testUserAlreadyRegisteredTrue() throws UserDoesNotExistException {
        assertTrue(UserService.checkUserExists("admin"));
    }

    @Test
    @DisplayName("Check if an user has already created an account")
    void testUserAlreadyRegisteredFalse() throws UserDoesNotExistException {
        assertFalse(UserService.checkUserExists("Calaretul123"));
    }

    @Test
    @DisplayName("Get an existing user")
    void testGetExistingUser() {
        assertNotNull(UserService.getUser("admin"));
    }

    @Test
    @DisplayName("Get a non-existing user")
    void testGetNonExistingUser() {
        assertNull(UserService.getUser("Calaretul321"));
    }

    @AfterEach
    void tearDown() {
        UserService.closeDatabase();
    }
}