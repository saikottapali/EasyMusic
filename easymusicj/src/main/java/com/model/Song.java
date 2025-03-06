package com.model;

import java.util.Date;

public class Song {
    private String title;
    private String composer;
    private String difficultyLevel;
    private Date date;
    private String sheetMusic; 

    // Constructor
    public Song(String title, String composer, String difficultyLevel, Date date, String sheetMusic) {
        this.title = title;
        this.composer = composer;
        this.difficultyLevel = difficultyLevel;
        this.date = date;
        this.sheetMusic = sheetMusic;
    }

    // Method to play the song
    public void play() {
        System.out.println("Playing song: " + title);
        //Implement Logic
    }

    // Method to upload the song
    public void upload() {
        System.out.println("Uploading song: " + title);
        //Implement Logic
    }

    // Method to download the song
    public void download() {
        System.out.println("Downloading song: " + title);
        //Implement Logic
    }

    // Method to display song details
    public void display() {
        System.out.println("Song: " + title);
        System.out.println("Composer: " + composer + ", Difficulty: " + difficultyLevel);
        System.out.println("Date: " + date);
        System.out.println("Sheet Music: " + sheetMusic); // 
    }

    // Getters and Setters
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSheetMusic() {
        return sheetMusic;
    }

    public void setSheetMusic(String sheetMusic) {
        this.sheetMusic = sheetMusic;
    }
}
