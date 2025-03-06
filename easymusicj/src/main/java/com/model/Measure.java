package com.model;

import java.util.ArrayList;

public class Measure {

    private ArrayList<Note> notes;
    private int tempo;  // Tempo for the measure
    private String timeSignature; //Ex. "4/4", "3/4", etc.

    // Constructor
    public Measure(int tempo, String timeSignature) {
        this.notes = new ArrayList<>();
        this.tempo = tempo;
        this.timeSignature = timeSignature;
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public void removeNote(Note note) {
        notes.remove(note);
    }

    // Method to display the notation for the measure
    public void displayNotation() {
        System.out.println("Time Signature: " + timeSignature + ", Tempo: " + tempo);
        for (Note note : notes) {
            System.out.println("Note: " + note.getPitch() + " - " + note.getDuration() + " beats");
        }
    }

    // Method to clear all notes in the measure
    public void clearMeasure() {
        notes.clear();
    }

    //Getters and Setters
    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public String getTimeSignature() {
        return timeSignature;
    }

    public void setTimeSignature(String timeSignature) {
        this.timeSignature = timeSignature;
    }
}
