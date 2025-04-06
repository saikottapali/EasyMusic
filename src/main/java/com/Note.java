package com.model;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class Note {
    private String pitch;   //Range of the note "C5" or "D#4"
    private String duration; //Duration in beats  q = 1.0 beats  h = 2.0 beats

    //Constructor
    public Note(String pitch, String duration) {
        this.pitch = pitch;
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

    //Getters and Setters
    public String getPitch() {
        return pitch;
    }

    public void setPitch(String pitch) {
        this.pitch = pitch;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

}

