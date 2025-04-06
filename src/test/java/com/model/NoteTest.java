package com.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

/**
* @author Cameron Long
* Tests Run by Bryson Sinclair
*/

public class NoteTest {

    @Test
    void testNoteCreation() {
        Note note = new Note("C5", "q");
        assertEquals("C5", note.getPitch());
        assertEquals("q", note.getDuration());
    }

    @Test
    void testSetPitch() {
        Note note = new Note("C5", "q");
        note.setPitch("D#4");
        assertEquals("D#4", note.getPitch());
    }

    @Test
    void testSetDuration() {
        Note note = new Note("C5", "q");
        note.setDuration("h");
        assertEquals("h", note.getDuration());
    }

    @Test
    void testChangePitch() {
        Note note = new Note("C5", "q");
        note.changePitch("F#3");
        assertEquals("F#3", note.getPitch());
    }

    @Test
    void testInvalidPitch() {
        Note note = new Note("C5", "q");
        note.setPitch("");  // Empty string
        assertNotEquals("", note.getPitch(), "Pitch should not be empty");

        note.setPitch(null);  // Null value
        assertNotNull(note.getPitch(), "Pitch should not be null");
    }

    @Test
    void testInvalidDuration() {
        Note note = new Note("C5", "q");
        note.setDuration("");  // Empty string
        assertNotEquals("", note.getDuration(), "Duration should not be empty");

        note.setDuration(null);  // Null value
        assertNotNull(note.getDuration(), "Duration should not be null");
    }

    @Test
    void testPlayDoesNotThrowException() {
        Note note = new Note("C5", "q");
        assertDoesNotThrow(() -> note.play(), "Playing a note should not throw exceptions");
    }
}

