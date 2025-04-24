package com.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SheetMusic {
    private UUID musicID;
    private String title;
    private String composer;
    private String difficultyLevel;
    private String clef;
    private int tempo;
    private List<Measure> measures;

    // Constructor
    public SheetMusic(UUID musicId, String title, String composer, String difficultyLevel, String clef,
        int tempo, List<Measure> measures) {
        this.musicID = UUID.randomUUID(); // Generate a new UUID for the music ID
        this.title = title;
        this.composer = composer;
        this.difficultyLevel = difficultyLevel;
        this.clef = clef;
        this.tempo = tempo;
        this.measures = measures != null ? measures : new ArrayList<>();
    }

    public void addMeasure(Measure measure) {
        measures.add(measure);
    }

    public void removeMeasure(Measure measure) {
        measures.remove(measure);
    }

    // Method to save sheet music to a text file
    public boolean saveToFile(Song dummySong) {
        if (dummySong != null) {
            try (FileWriter writer = new FileWriter(((SheetMusic) dummySong).getTitle() + ".txt")) {
                writer.write("Sheet Music: " + title + "\n");
                writer.write("Composer: " + composer + ", Difficulty: " + difficultyLevel + "\n");
                writer.write("Tempo: " + tempo + ", Clef: " + clef + "\n");
                for (Measure measure : measures) {
                    writer.write("Time Signature: " + measure.getTimeSignature() + ", Tempo: " + measure.getTempo() + "\n");
                    for (Note note : measure.getNotes()) {
                        writer.write("Note: " + note.getPitch() + " - " + note.getDuration() + " beats\n");
                    }
                    writer.write("\n"); // Separate measures with a blank line
                }

                writer.write("JFugue Notation: " + getJFugueNotation() + "\n");
                System.out.println("Sheet music saved to " + ((SheetMusic) dummySong).getTitle() + ".txt");
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

    public UUID getMusicID() {
        return musicID;
    }

    public void setMusicID(UUID musicID) {
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

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public String getClef() {
        return clef;
    }

    public void setClef(String clef) {
        this.clef = clef;
    }

    public List<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }

    
    
}



