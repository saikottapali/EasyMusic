package com.model;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
public class Instrument {
    private String name;
    private String description;
    private Note minNote;
    private Note maxNote;
    private String type; // Ex. piano, trumpet, etc.

    public Instrument(String name, String description, Note minNote, Note maxNote, String type) {
        this.name = name;
        this.description = description;
        this.minNote = minNote;
        this.maxNote = maxNote;
        this.type = type;
    }

    //Playing a note
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

    //Playing a Chord
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

    //Stop Playing All Music
    public void stop() {
        //Implement Logic here
    }

    //Record
    public void record() {
        //Implement Logic here
    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Note getMinNote() {
        return minNote;
    }

    public void setMinNote(Note minNote) {
        this.minNote = minNote;
    }

    public Note getMaxNote() {
        return maxNote;
    }

    public void setMaxNote(Note maxNote) {
        this.maxNote = maxNote;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
}
