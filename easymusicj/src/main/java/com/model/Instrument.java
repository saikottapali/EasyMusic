package com.model;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

/**
 * The Instrument class represents a musical instrument with various properties such as
 * name, description, range of notes (minNote and maxNote), and type.
 * It also provides methods for playing individual notes, playing chords, and potentially stopping or recording music.
 */
public class Instrument {
    private String name;
    private String description;
    private Note minNote;
    private Note maxNote;
    private String type;

    /**
     * Constructor to create an Instrument object with the given properties.
     * 
     * @param name The name of the instrument.
     * @param description A brief description of the instrument.
     * @param minNote The minimum note that the instrument can play.
     * @param maxNote The maximum note that the instrument can play.
     * @param type The type of instrument (e.g., "String", "Wind", "Percussion").
     */
    public Instrument(String name, String description, Note minNote, Note maxNote, String type) {
        this.name = name;
        this.description = description;
        this.minNote = minNote;
        this.maxNote = maxNote;
        this.type = type;
    }

    /**
     * Plays a single note using the instrument. The note is represented as a string with pitch and duration.
     * 
     * @param note The note to be played.
     */
    public void play(Note note) {
        if (note != null) {
            String noteString = note.getPitch() + "[" + note.getDuration() + "]";  // Example: "C5[1.0]"
            Pattern pattern = new Pattern(noteString);
            Player player = new Player();
            player.play(pattern);  // JFugue plays the note

            System.out.println("Playing note: " + note.getPitch());
        } else {
            System.out.println("Invalid note!");
        }
    }

    /**
     * Plays a chord using the instrument. The chord is represented by a list of notes.
     * 
     * @param chord The chord to be played.
     */
    public void play(Chord chord) {
        if (chord != null) {
            StringBuilder chordPattern = new StringBuilder();
            for (Note note : chord.getNotes()) {
                chordPattern.append(note.getPitch()).append("[").append(note.getDuration()).append("] ");
            }
            Pattern pattern = new Pattern(chordPattern.toString());
            Player player = new Player();
            player.play(pattern);  // JFugue plays the chord

            System.out.println("Playing chord: " + chord.getChordName());
        } else {
            System.out.println("Invalid chord!");
        }
    }

    /**
     * Stops all music currently playing. (To be implemented)
     */
    public void stop() {
        // Implement Logic here
    }

    /**
     * Starts recording the music being played. (To be implemented)
     */
    public void record() {
        // Implement Logic here
    }

    // Getters and Setters

    /**
     * Gets the name of the instrument.
     * 
     * @return The name of the instrument.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the instrument.
     * 
     * @param name The new name of the instrument.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the instrument.
     * 
     * @return The description of the instrument.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the instrument.
     * 
     * @param description The new description of the instrument.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the minimum note that the instrument can play.
     * 
     * @return The minimum note.
     */
    public Note getMinNote() {
        return minNote;
    }

    /**
     * Sets the minimum note that the instrument can play.
     * 
     * @param minNote The new minimum note.
     */
    public void setMinNote(Note minNote) {
        this.minNote = minNote;
    }

    /**
     * Gets the maximum note that the instrument can play.
     * 
     * @return The maximum note.
     */
    public Note getMaxNote() {
        return maxNote;
    }

    /**
     * Sets the maximum note that the instrument can play.
     * 
     * @param maxNote The new maximum note.
     */
    public void setMaxNote(Note maxNote) {
        this.maxNote = maxNote;
    }

    /**
     * Gets the type of the instrument.
     * 
     * @return The type of the instrument.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the instrument.
     * 
     * @param type The new type of the instrument.
     */
    public void setType(String type) {
        this.type = type;
    }
}
