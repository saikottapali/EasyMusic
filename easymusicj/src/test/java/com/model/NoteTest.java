package com.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NoteTest {

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
}
