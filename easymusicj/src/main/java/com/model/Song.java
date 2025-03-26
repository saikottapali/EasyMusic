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
    private Instrument instrument;
    private Date date;
    private SheetMusic sheetMusic; 
    private boolean isPrivate;
    private boolean isPublic;
    private boolean loggedIn;
    private List<String> comments;
    private List<String> songNotes;
    private List<String> tags;
    
    // Constructor
    public Song(UUID id, String title, String composer, String difficultyLevel, Instrument instrument, Date date, 
        SheetMusic sheetMusic, boolean isPrivate, boolean isPublic, boolean loggedIn, List<String> comments, List<String> songNotes, List<String> tags) {
            this.id = UUID.randomUUID();
            this.title = title;
            this.composer = composer;
            this.difficultyLevel = difficultyLevel;
            this.instrument = instrument;
            this.loggedIn = loggedIn;
            this.date = date;
            this.sheetMusic = sheetMusic;
            this.isPrivate = isPrivate;
            this.loggedIn = loggedIn;
            this.comments = comments;
            this.songNotes = songNotes;
            this.isPublic = isPublic;
            this.tags = tags;

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

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public List<String> getSongNotes() {
        return songNotes;
    }

    public void setSongNotes(List<String> songNotes) {
        this.songNotes = songNotes;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public boolean isIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    
}
