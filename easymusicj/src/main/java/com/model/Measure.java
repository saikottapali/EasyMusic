package com.model;

import java.util.ArrayList;
import org.jfugue.pattern.Pattern;

/**
 * The Measure class represents a musical measure, which contains a collection of notes,
 * a tempo setting, and a time signature. It provides methods for adding and removing notes,
 * converting the measure to a JFugue pattern or notation, and displaying the measure's details.
 */
public class Measure {

    private ArrayList<Note> notes;
    private int tempo;  // Tempo for the measure
    private String timeSignature; // e.g., "4/4", "3/4", etc.

    /**
     * Constructor to create a Measure object with the given tempo and time signature.
     * 
     * @param tempo The tempo of the measure.
     * @param timeSignature The time signature of the measure (e.g., "4/4", "3/4").
     */
    public Measure(int tempo, String timeSignature) {
        this.notes = new ArrayList<>();
        this.tempo = tempo;
        this.timeSignature = timeSignature;
    }

    /**
     * Adds a note to the measure.
     * 
     * @param note The note to be added to the measure.
     */
    public void addNote(Note note) {
        notes.add(note);
    }

    /**
     * Removes a note from the measure.
     * 
     * @param note The note to be removed from the measure.
     */
    public void removeNote(Note note) {
        notes.remove(note);
    }

    /**
     * Converts the measure to a JFugue pattern based on its notes, tempo, and time signature.
     * 
     * @return A JFugue Pattern representing the measure.
     */
    public Pattern toPattern() {
        StringBuilder patternString = new StringBuilder("T" + tempo + " ");
        for (Note note : notes) {
            patternString.append(note.getPitch()).append("/").append(note.getDuration()).append(" ");
        }
        return new Pattern(patternString.toString());
    }

    /**
     * Displays the notation for the measure, including time signature, tempo, and notes.
     */
    public void displayNotation() {
        System.out.println("Time Signature: " + timeSignature + ", Tempo: " + tempo);
        for (Note note : notes) {
            System.out.println("Note: " + note.getPitch() + " - " + note.getDuration() + " beats");
        }
    }

    /**
     * Converts the measure to JFugue notation as a string representation.
     * 
     * @return A string representing the measure in JFugue notation.
     */
    public String toJFugueNotation() {
        StringBuilder notation = new StringBuilder();
        for (Note note : notes) {
            notation.append(note.getPitch()).append("/").append(note.getDuration()).append(" ");
        }
        return notation.toString().trim();
    }

    /**
     * Clears all notes from the measure, effectively resetting it.
     */
    public void clearMeasure() {
        notes.clear();
    }

    // Getters and Setters

    /**
     * Gets the list of notes in the measure.
     * 
     * @return The list of notes.
     */
    public ArrayList<Note> getNotes() {
        return notes;
    }

    /**
     * Sets the list of notes in the measure.
     * 
     * @param notes The new list of notes for the measure.
     */
    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    /**
     * Gets the tempo of the measure.
     * 
     * @return The tempo of the measure.
     */
    public int getTempo() {
        return tempo;
    }

    /**
     * Sets the tempo of the measure.
     * 
     * @param tempo The new tempo for the measure.
     */
    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    /**
     * Gets the time signature of the measure.
     * 
     * @return The time signature of the measure.
     */
    public String getTimeSignature() {
        return timeSignature;
    }

    /**
     * Sets the time signature of the measure.
     * 
     * @param timeSignature The new time signature for the measure.
     */
    public void setTimeSignature(String timeSignature) {
        this.timeSignature = timeSignature;
    }
}
