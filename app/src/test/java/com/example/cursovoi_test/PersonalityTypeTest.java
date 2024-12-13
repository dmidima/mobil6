package com.example.cursovoi_test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PersonalityTypeTest {
    @Test
    public void testPersonalityTypeCreation() {
        PersonalityType type = new PersonalityType(1, "INTJ", "Стратег");
        assertNotNull(type);
        assertEquals(1, type.id);
        assertEquals("INTJ", type.name);
        assertEquals("Стратег", type.description);
    }
}

