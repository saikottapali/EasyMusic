package com.model;

import java.util.ArrayList;
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
    private List<String> songNotes;
    private List<String> tags;
    
    // Constructor
    public Song(UUID id, String title, String composer, SheetMusic sheetMusic, boolean isPrivate, ArrayList<String> songNotes) {
        this.id = id;  // Fix: Preserve the given UUID instead of overriding with random UUID
        this.title = title;
        this.composer = composer;
        this.sheetMusic = sheetMusic;
        this.isPrivate = isPrivate;
        this.songNotes = (songNotes != null) ? songNotes : new ArrayList<>();
        this.comments = new ArrayList<>();  // Initialize comments to prevent NullPointerException
        this.tags = new ArrayList<>();
        this.difficultyLevel = "UNKNOWN";  // Default difficulty level
        this.date = new Date();  // Default to current date
    }

    // Method to play the song
    public void play() {
        if (sheetMusic == null || sheetMusic.getJFugueNotation().isEmpty()) {
            System.out.println("No sheet music available for this song.");
            return;
        }
    
        Player player = new Player();
        Pattern songPattern = new Pattern(sheetMusic.getJFugueNotation());
    
        System.out.println("Playing: " + title + " by " + composer);
        player.play(songPattern);
    }
    
    // Method to upload the song
    public void upload() {
        if (!isPrivate) {
            System.out.println("Uploading song " + title);
        } else {
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
        
        if (sheetMusic != null) {
            System.out.println("Sheet Music Notation:\n" + sheetMusic.getJFugueNotation());
        } else {
            System.out.println("No sheet music available.");
        }
    }

    // Method to add a comment to the song
    public void addComment(String body, String author) {
        if (comments == null) {
            comments = new ArrayList<>();
        }
        comments.add(author + ": " + body);
    }

    // Getters and Setters
    public UUID getId() {
        return id;
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

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = (comments != null) ? comments : new ArrayList<>();
    }

    public List<String> getSongNotes() {
        return songNotes;
    }

    public void setSongNotes(List<String> songNotes) {
        this.songNotes = (songNotes != null) ? songNotes : new ArrayList<>();
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = (tags != null) ? tags : new ArrayList<>();
    }
}
