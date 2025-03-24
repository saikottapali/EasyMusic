package com.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class Song {

    private UUID id;
    private String title;
    private String composer;
    private String difficultyLevel;
    private Date date;
    private SheetMusic sheetMusic; 
    private boolean isPrivate;
    private List<String> comments;

    // Constructor
    public Song(String title, String composer, String difficultyLevel, Date date, 
    SheetMusic sheetMusic, boolean isPrivate, List<String> comments) {
        this.id = UUID.randomUUID(); //Generate new UUID
        this.title = title;
        this.composer = composer;
        this.difficultyLevel = difficultyLevel;
        this.date = date;
        this.sheetMusic = sheetMusic;
        this.isPrivate = isPrivate;
        this.comments = comments;

    }

    // Method to play the song
    public void play() {
        Player player = new Player();
        Pattern songPattern = new Pattern();

        for (Measure measure : sheetMusic.getMeasures()) {
            songPattern.add(measure.toPattern());
        }

        System.out.println("Playing: " + title + " by " + composer);
        player.play(songPattern);
    }

    // Method to upload the song
    public void upload() {
        if(!isPrivate) {
            System.out.println("Uploading song " + title);
        }
        else {
            System.out.println("Cannot upload private song: " + title);
        }
    }

    // Method to download the song
    public void download() {
        System.out.println("Downloading song: " + title);
    }

    // Method to display song details
    public void display() {
        System.out.println("Song: " + title);
        System.out.println("Composer: " + composer + ", Difficulty: " + difficultyLevel);
        System.out.println("Date: " + date);
        System.out.println("Sheet Music: " + sheetMusic); 
    }

    public void addComment(String body, String author) {
        comments.add(author + ":" + body);
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
        if (difficultyLevel != null) {
            this.difficultyLevel = difficultyLevel.toUpperCase();
        } else {
            System.out.println("Invalid difficulty level.");
        }  
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SheetMusic getSheetMusic() {
        return sheetMusic;
    }

    public void setSheetMusic(SheetMusic sheetMusic) {
        this.sheetMusic = sheetMusic;
    }

    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    

}
