package com.model;

import java.util.List;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class Chord {
    private String chordName;
    private List<Note> notes;
    private String chordType;
    private float duration;

    public Chord(String chordName, List<Note> notes, String chordType, float duration) {
        this.chordName = chordName;
        this.notes = notes;
        this.chordType = chordType;
        this.duration = duration;
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public void removeNote(Note note) {
        notes.remove(note);
    }

    public void setChordType(String chordType) {
        this.chordType = chordType;
    }

    public void playChord(Instrument instrument) {
        // Combine all notes into a single string for JFugue
        StringBuilder chordPattern = new StringBuilder();
        for (Note note : notes) {
            chordPattern.append(note.getPitch()).append("[").append(note.getDuration()).append("] ");
        }
        Pattern pattern = new Pattern(chordPattern.toString());
        Player player = new Player();
        player.play(pattern); 
    }

    public void displayChordNotation() {
        
        System.out.println("Displaying Chord Notation: " + chordName);
    }

    public String getChordDetails() {
        return "Chord: " + chordName + ", Type: " + chordType;
    }

    //Getters and Setters
    public String getChordName() {
        return chordName;
    }

    public void setChordName(String chordName) {
        this.chordName = chordName;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public String getChordType() {
        return chordType;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

}

