package com.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class Song {

    private UUID id;
    private String name;
    private String title;
    private String composer;
    private String difficultyLevel;
    private String instrument;
    private Date date;
    private SheetMusic sheetMusic; 
    private boolean isPrivate;
    private boolean loggedIn;
    private List<String> comments;
    private List<String> songNotes;

    // Constructor
    public Song(String title, String composer, String difficultyLevel, Instrument intsrument, Date date, 
    SheetMusic sheetMusic, boolean isPrivate, boolean loggedIn, List<String> comments, List<String> songNotes) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.composer = composer;
        this.difficultyLevel = difficultyLevel;
        this.instrument = instrument;
        this.loggedIn = loggedIn;
        this.date = date;
        this.sheetMusic = sheetMusic;
        this.isPrivate = isPrivate;
        this.comments = comments;
        this.songNotes = songNotes;

    }

    public Song(String title2, String composer2, String difficultyLevel2, Date date2, SheetMusic sheetMusic2,
            boolean isPrivate2, List<String> comments2) {
    }

    public Song(String createSongName, Instrument instrument2, Object object, boolean loggedIn2, Object object2) {
        //TODO Auto-generated constructor stub
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
        
        if (sheetMusic != null) {
            System.out.println("Sheet Music Notation:\n" + sheetMusic.getJFugueNotation());
        } else {
            System.out.println("No sheet music available.");
        }
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

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

<<<<<<< HEAD
    public List<String> getSongNotes() {
        return songNotes;
    }

    
=======
    public List<String> getComments() {
        return comments;
    }
>>>>>>> e217e15f0de49a2dd15fc21d3e362e1c9636efc5

    public void setComments(List<String> comments) {
        this.comments = comments;
    }
    
}
