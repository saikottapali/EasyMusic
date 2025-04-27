package com.model;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class Note {
    private String noteName; // E.g., "C5", "D#5", etc.
    private String pitch; // E.g., "C", "D#", etc.
    private int octave; // E.g., 4, 5, etc.
    private String duration; // Duration like "q", "e", etc.

    public Note(String noteName, String pitch, int octave, String duration) {
        this.noteName = noteName;
        this.pitch = pitch;
        this.octave = octave;
        this.duration = duration;
    }

    //Playing using jfugue
    public void play() {
        String noteString = pitch + duration; 
        Pattern pattern = new Pattern(noteString);
        Player player = new Player();
        player.play(pattern);  // JFugue plays the note
    }

    public void changePitch(String newPitch) {
        this.pitch = newPitch;
    }

    // Getters and Setters
    
    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = pitch + octave;
    }

    public String getPitch() {
        return pitch;
    }

    public void setPitch(String pitch) {
        this.pitch = pitch;
    }

    public int getOctave() {
        return octave;
    }

    public void setOctave(int octave) {
        this.octave = octave;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

}

