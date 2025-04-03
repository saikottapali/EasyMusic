package com.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SheetMusic {
    private String musicID;
    private String title;
    private String composer;
    private String difficultyLevel;
    private String clef;
    private int tempoNumerator;
    private int tempoDenominator;
    private ArrayList<Measure> measures;

    // Constructor
    public SheetMusic() {
        this.musicID = UUID.randomUUID().toString(); // Generate a new UUID for the music ID
        this.title = title;
        this.composer = composer;
        this.difficultyLevel = difficultyLevel;
        this.clef = clef;
        this.tempoNumerator = tempoNumerator;
        this.tempoDenominator = tempoDenominator;
        this.measures = measures != null ? measures : new ArrayList<>();
    }

    public SheetMusic(UUID randomUUID, String title2, String composer2, String difficultyLevel2, int tempoNumerator2,
            int tempoDenominator2, String clef2, List<Object> of) {
        //TODO Auto-generated constructor stub
    }

    public void addMeasure(Measure measure) {
        measures.add(measure);
    }

    public void removeMeasure(Measure measure) {
        measures.remove(measure);
    }

    // Method to save sheet music to a text file
    public boolean saveToFile(Song song) {
        if (song != null) {
            try (FileWriter writer = new FileWriter(song.getTitle() + ".txt")) {
                writer.write("Sheet Music: " + title + "\n");
                writer.write("Composer: " + composer + ", Difficulty: " + difficultyLevel + "\n");
                writer.write("Tempo: " + tempoNumerator + "/" + tempoDenominator + ", Clef: " + clef + "\n");
                for (Measure measure : measures) {
                    writer.write("Time Signature: " + measure.getTimeSignature() + ", Tempo: " + measure.getTempo() + "\n");
                    for (Note note : measure.getNotes()) {
                        writer.write("Note: " + note.getPitch() + " - " + note.getDuration() + " beats\n");
                    }
                    writer.write("\n"); // Separate measures with a blank line
                }

                writer.write("JFugue Notation: " + getJFugueNotation() + "\n");
                System.out.println("Sheet music saved to " + song.getTitle() + ".txt");
                return true;

            } catch (IOException e) {
                System.out.println("Error saving sheet music: " + e.getMessage());
            }
        }
        return false;
    }

    // Helper method to generate JFugue notation
    public String getJFugueNotation() {
        StringBuilder notation = new StringBuilder();
        for (Measure measure : measures) {
            notation.append(measure.toJFugueNotation()).append(" | ");  // Adding measure separators
        }
        return notation.toString().trim();
    }

    public String getMusicID() {
        return musicID;
    }

    public void setMusicID(String musicID) {
        this.musicID = musicID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public int getTempoNumerator() {
        return tempoNumerator;
    }

    public void setTempoNumerator(int tempoNumerator) {
        this.tempoNumerator = tempoNumerator;
    }

    public int getTempoDenominator() {
        return tempoDenominator;
    }

    public void setTempoDenominator(int tempoDenominator) {
        this.tempoDenominator = tempoDenominator;
    }

    public String getClef() {
        return clef;
    }

    public void setClef(String clef) {
        this.clef = clef;
    }

    public ArrayList<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(ArrayList<Measure> measures) {
        this.measures = measures;
    }

    
    
}


