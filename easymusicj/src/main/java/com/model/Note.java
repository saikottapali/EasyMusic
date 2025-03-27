package com.model;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

/**
 * The Note class represents a musical note with a specific pitch, duration, volume, 
 * and playback state. It provides methods to play the note, change its pitch and volume, 
 * and manage its playback state.
 */
public class Note {
    private String pitch;   // Range of the note (e.g., "C5", "D#4")
    private double duration; // Duration in beats (e.g., quarter note = 1.0, half note = 2.0)
    private int volume;      // Volume of the note
    private boolean isPlaying; // Whether the note is currently playing

    /**
     * Constructor to initialize a Note with the specified pitch, duration, and volume.
     * 
     * @param pitch The pitch of the note (e.g., "C5", "D#4").
     * @param duration The duration of the note in beats (e.g., 1.0 for a quarter note).
     * @param volume The volume of the note.
     */
    public Note(String pitch, double duration, int volume) {
        this.pitch = pitch;
        this.duration = duration;
        this.volume = volume;
        this.isPlaying = false;
    }

    /**
     * Plays the note using JFugue.
     * The note will be played with the specified pitch, duration, and volume.
     */
    public void play() {
        String noteString = pitch + "[" + duration + "]" + " !" + volume;  
        Pattern pattern = new Pattern(noteString);
        Player player = new Player();
        player.play(pattern);  // JFugue plays the note
        this.isPlaying = true;
    }

    /**
     * Stops the note from playing.
     */
    public void stop() {
        this.isPlaying = false;
    }

    /**
     * Changes the pitch of the note.
     * 
     * @param newPitch The new pitch for the note.
     */
    public void changePitch(String newPitch) {
        this.pitch = newPitch;
    }

    /**
     * Changes the volume of the note.
     * 
     * @param newVolume The new volume for the note.
     */
    public void changeVolume(int newVolume) {
        this.volume = newVolume;
    }

    // Getters and Setters

    /**
     * Gets the pitch of the note.
     * 
     * @return The pitch of the note (e.g., "C5").
     */
    public String getPitch() {
        return pitch;
    }

    /**
     * Sets the pitch of the note.
     * 
     * @param pitch The new pitch of the note.
     */
    public void setPitch(String pitch) {
        this.pitch = pitch;
    }

    /**
     * Gets the duration of the note.
     * 
     * @return The duration of the note in beats.
     */
    public double getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the note.
     * 
     * @param duration The new duration of the note in beats.
     */
    public void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * Gets the volume of the note.
     * 
     * @return The volume of the note.
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Sets the volume of the note.
     * 
     * @param volume The new volume of the note.
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * Checks if the note is currently playing.
     * 
     * @return True if the note is playing, false otherwise.
     */
    public boolean isPlaying() {
        return isPlaying;
    }

    /**
     * Sets the playback state of the note.
     * 
     * @param isPlaying The playback state (true if playing, false if stopped).
     */
    public void setPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }
}
