package com.model;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
* @author Savanna Welch
* Tests run by Bryson Sinclair
*/

public class UserTest {
    
    private User user;

    @BeforeEach
    void setUp() {
        user = new User(null, "Savanna", "password123", null, null, null, 0, null, false);
    }

    @AfterEach
    void tearDown() {
        user = null;
    }

    @Test
    void testUserCreation() {
        assertNotNull(user, "User object should be created.");
        assertEquals("Savanna", user.getUsername(), "Username should be set correctly.");
    }

    @Test
    void testSetPassword() {
    user.setPassword("newPassword");
    // If there's a getPassword() method, use this:
    assertEquals("newPassword", user.getPassword(), "Password should be updated.");
}

   
}

