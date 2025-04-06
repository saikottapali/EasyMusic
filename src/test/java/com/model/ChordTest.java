package com.model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
* @author Savanna Welch
*/

class ChordTest {

    @Test
    void testChordCreation() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note("C5", "q"));
        notes.add(new Note("E5", "q"));
        notes.add(new Note("G5", "q"));

        Chord chord = new Chord("C Major", notes, "major", "q");

        assertEquals("C Major", chord.getChordName());
        assertEquals("major", chord.getChordType());
        assertEquals("q", chord.getDuration());
        assertEquals(3, chord.getNotes().size());
    }

    @Test
    void testAddNote() {
        List<Note> notes = new ArrayList<>();
        Chord chord = new Chord("C Major", notes, "major", "q");

        Note newNote = new Note("D5", "q");
        chord.addNote(newNote);

        assertTrue(chord.getNotes().contains(newNote));
        assertEquals(1, chord.getNotes().size());
    }

    @Test
    void testRemoveNote() {
        List<Note> notes = new ArrayList<>();
        Note note = new Note("C5", "q");
        notes.add(note);
        Chord chord = new Chord("C Major", notes, "major", "q");

        chord.removeNote(note);
        assertFalse(chord.getNotes().contains(note));
        assertEquals(0, chord.getNotes().size());
    }

    @Test
    void testSetChordType() {
        List<Note> notes = new ArrayList<>();
        Chord chord = new Chord("C Major", notes, "major", "q");

        chord.setChordType("minor");
        assertEquals("minor", chord.getChordType());
    }

    @Test
    void testSetChordName() {
        List<Note> notes = new ArrayList<>();
        Chord chord = new Chord("C Major", notes, "major", "q");

        chord.setChordName("C Minor");
        assertEquals("C Minor", chord.getChordName());
    }

    @Test
    void testSetDuration() {
        List<Note> notes = new ArrayList<>();
        Chord chord = new Chord("C Major", notes, "major", "q");

        chord.setDuration("q");
        assertEquals("q", chord.getDuration());
    }

    @Test
    void testGetChordDetails() {
        List<Note> notes = new ArrayList<>();
        Chord chord = new Chord("G Major", notes, "major", "q");

        assertEquals("Chord: G Major, Type: major", chord.getChordDetails());
    }

    @Test
    void testDisplayChordNotation() {
        List<Note> notes = new ArrayList<>();
        Chord chord = new Chord("C Major", notes, "major", "q");

        assertDoesNotThrow(() -> chord.displayChordNotation());
    }
}


