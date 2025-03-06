package com.model;

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

    // Method to upload the sheet music
    public void upload() {
        System.out.println("Uploading sheet music: " + title);
        // Logic for uploading
    }

    // Method to download the sheet music
    public void download() {
        System.out.println("Downloading sheet music: " + title);
        // Logic for downloading
    }

    // Method to display the sheet music's information and notation
    public void display() {
        System.out.println("Sheet Music: " + title);
        System.out.println("Composer: " + composer + ", Difficulty: " + difficultyLevel);
        System.out.println("Tempo: " + tempoNumerator + "/" + tempoDenominator + ", Clef: " + clef);
        System.out.println("Notation Type: " + notationType);
        for (Measure measure : measures) {
            measure.displayNotation();
        }
    }

    // Getters and Setters
    public int getMusicID() {
        return musicID;
    }

    public void setMusicID(int musicID) {
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

    public String getNotationType() {
        return notationType;
    }

    public void setNotationType(String notationType) {
        this.notationType = notationType;
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

