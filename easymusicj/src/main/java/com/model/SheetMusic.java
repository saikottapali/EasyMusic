package com.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SheetMusic {
    private int musicID;
    private String title;
    private String composer;
    private String difficultyLevel;
    private String notationType;
    private int tempoNumerator;
    private int tempoDenominator;
    private String clef;
    private ArrayList<Measure> measures;

    // Constructor
    public SheetMusic(int musicID, String title, String composer, String difficultyLevel, String notationType, 
                      int tempoNumerator, int tempoDenominator, String clef) {
        this.musicID = musicID;
        this.title = title;
        this.composer = composer;
        this.difficultyLevel = difficultyLevel;
        this.notationType = notationType;
        this.tempoNumerator = tempoNumerator;
        this.tempoDenominator = tempoDenominator;
        this.clef = clef;
        this.measures = new ArrayList<>();
    }

    public void addMeasure(Measure measure) {
        measures.add(measure);
    }

    public void removeMeasure(Measure measure) {
        measures.remove(measure);
    }

    // Method to save sheet music to a text file
    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Sheet Music: " + title + "\n");
            writer.write("Composer: " + composer + ", Difficulty: " + difficultyLevel + "\n");
            writer.write("Tempo: " + tempoNumerator + "/" + tempoDenominator + ", Clef: " + clef + "\n");
            writer.write("Notation Type: " + notationType + "\n\n");

            for (Measure measure : measures) {
                writer.write("Time Signature: " + measure.getTimeSignature() + ", Tempo: " + measure.getTempo() + "\n");
                for (Note note : measure.getNotes()) {
                    writer.write("Note: " + note.getPitch() + " - " + note.getDuration() + " beats\n");
                }
                writer.write("\n"); // Separate measures with a blank line
            }

            writer.write("JFugue Notation: " + getJFugueNotation() + "\n");
            System.out.println("Sheet music saved to " + filename);

        } catch (IOException e) {
            System.out.println("Error saving sheet music: " + e.getMessage());
        }
    }

    // Helper method to generate JFugue notation
    public String getJFugueNotation() {
        StringBuilder notation = new StringBuilder();
        for (Measure measure : measures) {
            notation.append(measure.toJFugueNotation()).append(" | ");  // Adding measure separators
        }
        return notation.toString().trim();
    }
    
}


