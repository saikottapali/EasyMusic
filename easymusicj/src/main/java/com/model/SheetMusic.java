package com.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * The SheetMusic class represents a piece of sheet music, which includes details
 * like the title, composer, difficulty level, tempo, clef, and measures.
 * It also allows adding and removing measures and saving the sheet music to a file.
 */
public class SheetMusic {
    private UUID musicID;
    private String title;
    private String composer;
    private String difficultyLevel;
    private String notationType;
    private int tempoNumerator;
    private int tempoDenominator;
    private String clef;
    private ArrayList<Measure> measures;

    /**
     * Constructor to initialize the SheetMusic object with the provided details.
     * 
     * @param musicID The unique identifier for the sheet music.
     * @param title The title of the sheet music.
     * @param composer The composer of the sheet music.
     * @param difficultyLevel The difficulty level of the sheet music.
     * @param notationType The type of notation used (e.g., standard, tab).
     * @param tempoNumerator The numerator of the tempo (beats per measure).
     * @param tempoDenominator The denominator of the tempo (note value).
     * @param clef The clef used in the sheet music (e.g., treble, bass).
     * @param measures The list of measures contained in the sheet music.
     */
    public SheetMusic(UUID musicID, String title, String composer, String difficultyLevel, String notationType, 
        int tempoNumerator, int tempoDenominator, String clef, ArrayList<Measure> measures) {
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

    /**
     * Adds a measure to the sheet music.
     * 
     * @param measure The measure to be added to the sheet music.
     */
    public void addMeasure(Measure measure) {
        measures.add(measure);
    }

    /**
     * Removes a measure from the sheet music.
     * 
     * @param measure The measure to be removed from the sheet music.
     */
    public void removeMeasure(Measure measure) {
        measures.remove(measure);
    }

    /**
     * Saves the sheet music to a text file with the given filename.
     * The file will contain information about the sheet music, its measures, and JFugue notation.
     * 
     * @param filename The name of the file to save the sheet music to.
     */
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

    /**
     * Generates the JFugue notation for the sheet music based on its measures.
     * 
     * @return A string representing the JFugue notation for the sheet music.
     */
    public String getJFugueNotation() {
        StringBuilder notation = new StringBuilder();
        for (Measure measure : measures) {
            notation.append(measure.toJFugueNotation()).append(" | ");  // Adding measure separators
        }
        return notation.toString().trim();
    }

    // Getters and Setters

    /**
     * Gets the unique ID of the sheet music.
     * 
     * @return The music ID.
     */
    public UUID getMusicID() {
        return musicID;
    }

    /**
     * Sets the unique ID of the sheet music.
     * 
     * @param musicID The new music ID.
     */
    public void setMusicID(UUID musicID) {
        this.musicID = musicID;
    }

    /**
     * Gets the title of the sheet music.
     * 
     * @return The title of the sheet music.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the sheet music.
     * 
     * @param title The new title of the sheet music.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the composer of the sheet music.
     * 
     * @return The composer of the sheet music.
     */
    public String getComposer() {
        return composer;
    }

    /**
     * Sets the composer of the sheet music.
     * 
     * @param composer The new composer of the sheet music.
     */
    public void setComposer(String composer) {
        this.composer = composer;
    }

    /**
     * Gets the difficulty level of the sheet music.
     * 
     * @return The difficulty level of the sheet music.
     */
    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    /**
     * Sets the difficulty level of the sheet music.
     * 
     * @param difficultyLevel The new difficulty level.
     */
    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    /**
     * Gets the notation type of the sheet music.
     * 
     * @return The notation type of the sheet music.
     */
    public String getNotationType() {
        return notationType;
    }

    /**
     * Sets the notation type of the sheet music.
     * 
     * @param notationType The new notation type.
     */
    public void setNotationType(String notationType) {
        this.notationType = notationType;
    }

    /**
     * Gets the numerator of the tempo.
     * 
     * @return The tempo numerator.
     */
    public int getTempoNumerator() {
        return tempoNumerator;
    }

    /**
     * Sets the numerator of the tempo.
     * 
     * @param tempoNumerator The new tempo numerator.
     */
    public void setTempoNumerator(int tempoNumerator) {
        this.tempoNumerator = tempoNumerator;
    }

    /**
     * Gets the denominator of the tempo.
     * 
     * @return The tempo denominator.
     */
    public int getTempoDenominator() {
        return tempoDenominator;
    }

    /**
     * Sets the denominator of the tempo.
     * 
     * @param tempoDenominator The new tempo denominator.
     */
    public void setTempoDenominator(int tempoDenominator) {
        this.tempoDenominator = tempoDenominator;
    }

    /**
     * Gets the clef used in the sheet music.
     * 
     * @return The clef.
     */
    public String getClef() {
        return clef;
    }

    /**
     * Sets the clef used in the sheet music.
     * 
     * @param clef The new clef.
     */
    public void setClef(String clef) {
        this.clef = clef;
    }

    /**
     * Gets the list of measures in the sheet music.
     * 
     * @return The list of measures.
     */
    public ArrayList<Measure> getMeasures() {
        return measures;
    }

    /**
     * Sets the list of measures in the sheet music.
     * 
     * @param measures The new list of measures.
     */
    public void setMeasures(ArrayList<Measure> measures) {
        this.measures = measures;
    }
}
