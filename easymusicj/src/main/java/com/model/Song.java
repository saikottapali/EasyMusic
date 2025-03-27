package com.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

/**
 * Represents a musical song with attributes such as title, composer, 
 * difficulty level, instrument, and sheet music.
 * Provides methods for playing, uploading, downloading, and displaying song details.
 */
public class Song {

    private UUID id;
    private String title;
    private String composer;
    private String difficultyLevel;
    private Instrument instrument;
    private Date date;
    private SheetMusic sheetMusic; 
    private boolean isPrivate;
    private List<String> comments;
    private List<String> songNotes;
    private List<String> tags;
    
    /**
     * Constructs a new Song object with the given details.
     *
     * @param title The title of the song.
     * @param instrument The instrument used in the song.
     * @param composer The composer of the song.
     * @param isPrivate Indicates whether the song is private.
     * @param songNotes A list of notes in the song.
     */
    public Song(String title, Instrument instrument, String composer, boolean isPrivate, ArrayList<String> songNotes) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.instrument = instrument;
        this.composer = composer;
        this.isPrivate = isPrivate;
        this.songNotes = songNotes;
    }

    /**
     * Constructs a new Song object with detailed parameters.
     *
     * @param id The unique identifier for the song.
     * @param title The title of the song.
     * @param composer The composer of the song.
     * @param difficultyLevel The difficulty level of the song.
     * @param instrument The instrument used in the song.
     * @param date The date the song was created or uploaded.
     * @param sheetMusic The sheet music associated with the song.
     * @param isPrivate Indicates whether the song is private.
     * @param comments A list of comments about the song.
     * @param songNotes A list of notes in the song.
     * @param tags A list of tags associated with the song.
     */
    public Song(UUID id2, String title2, String composer2, String difficultyLevel2, Instrument instrument2, Date date2,
                SheetMusic sheetMusic2, boolean isPrivate2, List<String> comments2, List<String> songNotes2,
                List<String> tags2) {
        //TODO Auto-generated constructor stub
    }

    /**
     * Plays the song using the sheet music's JFugue notation.
     * If no sheet music is available, an error message will be shown.
     */
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

    /**
     * Uploads the song if it is not marked as private.
     * Displays a message indicating whether the song was uploaded.
     */
    public void upload() {
        if(!isPrivate) {
            System.out.println("Uploading song " + title);
        } else {
            System.out.println("Cannot upload private song: " + title);
        }
    }

    /**
     * Downloads the song and displays a message indicating the download process.
     */
    public void download() {
        System.out.println("Downloading song: " + title);
    }

    /**
     * Displays the details of the song including title, composer, difficulty,
     * date, and sheet music notation.
     */
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

    /**
     * Adds a comment to the song in the format "author: body".
     *
     * @param body The body of the comment.
     * @param author The author of the comment.
     */
    public void addComment(String body, String author) {
        comments.add(author + ":" + body);
    }

    // Getters and Setters

    /**
     * Gets the title of the song.
     *
     * @return The title of the song.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the song.
     *
     * @param title The new title of the song.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the composer of the song.
     *
     * @return The composer of the song.
     */
    public String getComposer() {
        return composer;
    }

    /**
     * Sets the composer of the song.
     *
     * @param composer The new composer of the song.
     */
    public void setComposer(String composer) {
        this.composer = composer;
    }

    /**
     * Gets the difficulty level of the song.
     *
     * @return The difficulty level of the song.
     */
    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    /**
     * Sets the difficulty level of the song. Converts the difficulty level to uppercase.
     *
     * @param difficultyLevel The new difficulty level of the song.
     */
    public void setDifficultyLevel(String difficultyLevel) {
        if (difficultyLevel != null) {
            this.difficultyLevel = difficultyLevel.toUpperCase();
        } else {
            System.out.println("Invalid difficulty level.");
        }
    }

    /**
     * Gets the date the song was created or uploaded.
     *
     * @return The date the song was created or uploaded.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date the song was created or uploaded.
     *
     * @param date The new date for the song.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the sheet music associated with the song.
     *
     * @return The sheet music associated with the song.
     */
    public SheetMusic getSheetMusic() {
        return sheetMusic;
    }

    /**
     * Sets the sheet music for the song.
     *
     * @param sheetMusic The new sheet music for the song.
     */
    public void setSheetMusic(SheetMusic sheetMusic) {
        this.sheetMusic = sheetMusic;
    }

    /**
     * Sets the privacy status of the song.
     *
     * @param isPrivate True if the song is private, false otherwise.
     */
    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    /**
     * Gets the unique identifier of the song.
     *
     * @return The unique identifier of the song.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the song.
     *
     * @param id The new unique identifier for the song.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Gets the list of comments associated with the song.
     *
     * @return The list of comments for the song.
     */
    public List<String> getComments() {
        return comments;
    }

    /**
     * Sets the list of comments for the song.
     *
     * @param comments The new list of comments.
     */
    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    /**
     * Gets the list of song notes.
     *
     * @return The list of song notes.
     */
    public List<String> getSongNotes() {
        return songNotes;
    }

    /**
     * Sets the list of song notes.
     *
     * @param songNotes The new list of song notes.
     */
    public void setSongNotes(List<String> songNotes) {
        this.songNotes = songNotes;
    }

    /**
     * Checks if the song is private.
     *
     * @return True if the song is private, false otherwise.
     */
    public boolean isPrivate() {
        return isPrivate;
    }

    /**
     * Gets the instrument used in the song.
     *
     * @return The instrument used in the song.
     */
    public Instrument getInstrument() {
        return instrument;
    }

    /**
     * Sets the instrument used in the song.
     *
     * @param instrument The new instrument for the song.
     */
    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    /**
     * Gets the list of tags associated with the song.
     *
     * @return The list of tags for the song.
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * Sets the list of tags for the song.
     *
     * @param tags The new list of tags.
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
