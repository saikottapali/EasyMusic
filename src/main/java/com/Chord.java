package com.model;

import java.util.List;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

/**
 * Represents a musical chord consisting of a name, notes, chord type, and duration.
 * Provides functionality to add and remove notes, play the chord using JFugue, and display its notation.
 */
public class Chord {
    
    /** The name of the chord (e.g., "C", "Gm", etc.) */
    private String chordName;
    
    /** The list of notes that make up the chord */
    private List<Note> notes;
    
    /** The type of the chord (e.g., "major", "minor", etc.) */
    private String chordType;
    
    /** The duration of the chord (in beats or musical duration) */
    private String duration;

    /**
     * Constructs a new Chord with the specified chord name, notes, chord type, and duration.
     * 
     * @param chordName The name of the chord (e.g., "C", "Gm").
     * @param notes A list of notes that make up the chord.
     * @param chordType The type of the chord (e.g., "major", "minor").
     * @param duration The duration of the chord.
     */
    public Chord(String chordName, List<Note> notes, String chordType, String duration) {
        this.chordName = chordName;
        this.notes = notes;
        this.chordType = chordType;
        this.duration = duration;
    }

    /**
     * Adds a note to the chord.
     * 
     * @param note The note to add to the chord.
     */
    public void addNote(Note note) {
        notes.add(note);
    }

    /**
     * Removes a note from the chord.
     * 
     * @param note The note to remove from the chord.
     */
    public void removeNote(Note note) {
        notes.remove(note);
    }

    /**
     * Sets the type of the chord (e.g., "major", "minor").
     * 
     * @param chordType The type of the chord.
     */
    public void setChordType(String chordType) {
        this.chordType = chordType;
    }

    /**
     * Plays the chord using the specified instrument.
     * The chord is represented as a pattern of notes, each with its pitch and duration.
     * 
     * @param instrument The instrument to use for playing the chord.
     */
    public void playChord() {
        // Combine all notes into a single string for JFugue
        StringBuilder chordPattern = new StringBuilder();
        for (Note note : notes) {
            chordPattern.append(note.getPitch()).append("[").append(note.getDuration()).append("] ");
        }
        Pattern pattern = new Pattern(chordPattern.toString());
        Player player = new Player();
        player.play(pattern); 
    }

    /**
     * Displays the chord notation, which includes the chord name.
     */
    public void displayChordNotation() {
        System.out.println("Displaying Chord Notation: " + chordName);
    }

    /**
     * Returns a string representation of the chord details, including its name and type.
     * 
     * @return A string describing the chord name and type.
     */
    public String getChordDetails() {
        return "Chord: " + chordName + ", Type: " + chordType;
    }

    // Getters and Setters

    /**
     * Returns the name of the chord.
     * 
     * @return The name of the chord.
     */
    public String getChordName() {
        return chordName;
    }

    /**
     * Sets the name of the chord.
     * 
     * @param chordName The new name of the chord.
     */
    public void setChordName(String chordName) {
        this.chordName = chordName;
    }

    /**
     * Returns the list of notes that make up the chord.
     * 
     * @return The list of notes.
     */
    public List<Note> getNotes() {
        return notes;
    }

    /**
     * Sets the list of notes that make up the chord.
     * 
     * @param notes The new list of notes.
     */
    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    /**
     * Returns the type of the chord (e.g., "major", "minor").
     * 
     * @return The chord type.
     */
    public String getChordType() {
        return chordType;
    }

    /**
     * Returns the duration of the chord.
     * 
     * @return The duration of the chord.
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the chord.
     * 
     * @param duration The new duration of the chord.
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }
}

