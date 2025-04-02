package com.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class MeasureTest {

    @Test
    void testMeasureCreation() {
        // Creating test notes
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(new Note("C4", "q"));
        notes.add(new Note("E4", "h"));

        // Creating a Measure object
        Measure measure = new Measure(notes, 120, "4/4");

        // Assertions to verify object creation
        assertNotNull(measure);
        assertEquals(120, measure.getTempo());
        assertEquals("4/4", measure.getTimeSignature());
        assertEquals(2, measure.getNotes().size());
    }

    @Test
    void testAddNote() {
        ArrayList<Note> notes = new ArrayList<>();
        Measure measure = new Measure(notes, 100, "3/4");

        Note newNote = new Note("G4", "w");
        measure.addNote(newNote);

        assertEquals(1, measure.getNotes().size());
        assertEquals("G4", measure.getNotes().get(0).getPitch());
    }

    @Test
    void testRemoveNote() {
        ArrayList<Note> notes = new ArrayList<>();
        Note note1 = new Note("A4", "q");
        Note note2 = new Note("B4", "h");
        notes.add(note1);
        notes.add(note2);

        Measure measure = new Measure(notes, 90, "6/8");

        measure.removeNote(note1);
        assertEquals(1, measure.getNotes().size());
        assertEquals("B4", measure.getNotes().get(0).getPitch());
    }

    @Test
    void testClearMeasure() {
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(new Note("C5", "e"));
        notes.add(new Note("D5", "s"));

        Measure measure = new Measure(notes, 110, "2/4");

        measure.clearMeasure();
        assertEquals(0, measure.getNotes().size());
    }

    @Test
    void testToJFugueNotation() {
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(new Note("F4", "q"));
        notes.add(new Note("G4", "h"));

        Measure measure = new Measure(notes, 130, "4/4");

        String notation = measure.toJFugueNotation();
        assertEquals("F4/q G4/h", notation);
    }
}
