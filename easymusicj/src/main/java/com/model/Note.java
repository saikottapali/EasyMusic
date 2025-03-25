package com.model;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class Note {
    private String pitch;   //Range of the note "C5" or "D#4"
    private double duration; //Duration in beats  quarter note = 1.0 beats  half note = 2.0 beats
    private int volume;  
    private boolean isPlaying;

    //Constructor
    public Note(String pitch, double duration, int volume) {
        this.pitch = pitch;
        this.duration = duration;
        this.volume = volume;
        this.isPlaying = false;
    }

    //Playing using jfugue
<<<<<<< HEAD
    
    public Note(String pitch2, double d) {
        //TODO Auto-generated constructor stub
    }

=======
>>>>>>> e217e15f0de49a2dd15fc21d3e362e1c9636efc5
    public void play() {
        String noteString = pitch + "[" + duration + "]" + " !" + volume;  
        Pattern pattern = new Pattern(noteString);
        Player player = new Player();
        player.play(pattern);  // JFugue plays the note
        this.isPlaying = true;
    }

    public void stop() {
        this.isPlaying = false;
    }

    public void changePitch(String newPitch) {
        this.pitch = newPitch;
    }

    public void changeVolume(int newVolume) {
        this.volume = newVolume;
    }

    //Getters and Setters
    public String getPitch() {
        return pitch;
    }

    public void setPitch(String pitch) {
        this.pitch = pitch;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }
}
