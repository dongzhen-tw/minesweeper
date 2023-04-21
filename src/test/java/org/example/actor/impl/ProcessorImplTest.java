package org.example.actor.impl;

import org.example.actor.Processor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProcessorImplTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetValidIndex() {
        Processor p = new ProcessorImpl();
        assertTrue(p.getValidIndex("A1").isPresent());
        assertEquals(0, p.getValidIndex("A1").get()[0]);
        assertEquals(0, p.getValidIndex("A1").get()[1]);
        assertTrue(p.getValidIndex("B10").isPresent());

        assertFalse(p.getValidIndex("A100").isPresent());
        assertFalse(p.getValidIndex("Y0").isPresent());
        assertFalse(p.getValidIndex("A01").isPresent());
    }
}
