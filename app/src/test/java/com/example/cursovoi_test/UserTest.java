package com.example.cursovoi_test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserTest {
    @Test
    public void testUserCreation() {
        User user = new User("testuser", "test@example.com", "password");
        assertNotNull(user);
        assertEquals("testuser", user.login);
        assertEquals("test@example.com", user.email);
        assertEquals("password", user.password);
    }
}

