package com.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * The MusicCreation class is responsible for managing the creation, saving, uploading, and playing of music.
 * It handles the creation of songs, and the management of instruments, songs, and sheet music.
 */
public class MusicCreation {
    private List<Instrument> instruments;
    private List<Song> songs;
    private List<SheetMusic> sheetMusic;

    /**
     * Constructor to initialize the MusicCreation object with empty lists for instruments, songs, and sheet music.
     */
    public MusicCreation() {
        this.instruments = new ArrayList<>();
        this.songs = new ArrayList<>();
        this.sheetMusic = new ArrayList<>();
    }

    /**
     * Creates a new song and adds it to the song list.
     * 
     * @param id The unique identifier for the song.
     * @param title The title of the song.
     * @param composer The composer of the song.
     * @param difficultyLevel The difficulty level of the song.
     * @param date The date the song was created.
     * @param sheetMusic The sheet music associated with the song.
     * @param isPrivate Indicates whether the song is private.
     * @param comments A list of comments for the song.
     * @param instrument The instrument used in the song.
     * @param songNotes A list of song notes.
     * @param loggedIn Indicates whether the user is logged in.
     * @param tags A list of tags for the song.
     * @return The newly created Song object.
     */
    public Song createMusic(UUID id, String title, String composer, String difficultyLevel, Date date, 
    SheetMusic sheetMusic, boolean isPrivate, List<String> comments, Instrument instrument, List<String> songNotes, boolean loggedIn, List<String> tags) {
        Song newSong = new Song(id, title, composer, difficultyLevel, instrument, date, sheetMusic, isPrivate,
        comments, songNotes, tags);
        songs.add(newSong);
        System.out.println("New song created: " + title);
        return newSong; 
    }

    /**
     * Saves a song if it exists in the songs list.
     * 
     * @param song The song to be saved.
     * @return True if the song was found and saved, otherwise false.
     */
    public boolean saveMusic(Song song) {
        if (songs.contains(song)) {
            System.out.println("Music saved: " + song.getTitle());
            return true;
        }
        System.out.println("Song not found, save failed.");
        return false;
    }

    /**
     * Uploads a song if it exists in the songs list.
     * 
     * @param song The song to be uploaded.
     * @return True if the song was found and uploaded, otherwise false.
     */
    public boolean uploadSong(Song song) {
        if (songs.contains(song)) {
            song.upload();
            return true;
        }
        System.out.println("Song not found, upload failed.");
        return false;
    }

    /**
     * Plays a song if it exists in the songs list.
     * 
     * @param song The song to be played.
     */
    public void playSong(Song song) {
        if (songs.contains(song)) {
            song.play();
        } else {
            System.out.println("Song not found, cannot play.");
        }
    }

    /**
     * Downloads a song if it exists in the songs list.
     * 
     * @param song The song to be downloaded.
     * @return True if the song was found and downloaded, otherwise false.
     */
    public boolean downloadSong(Song song) {
        if (songs.contains(song)) {
            song.download();
            return true;
        }
        System.out.println("Song not found, download failed.");
        return false;
    }

    /**
     * Searches for a song by its title.
     * 
     * @param title The title of the song to search for.
     * @return The song if found, otherwise null.
     */
    public Song searchSong(String title) {
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                return song;
            }
        }
        return null;
    }

    // Getters and Setters

    /**
     * Gets the list of instruments available for music creation.
     * 
     * @return The list of instruments.
     */
    public List<Instrument> getInstruments() {
        return instruments;
    }

    /**
     * Sets the list of instruments available for music creation.
     * 
     * @param instruments The new list of instruments.
     */
    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
    }

    /**
     * Gets the list of songs in the music creation system.
     * 
     * @return The list of songs.
     */
    public List<Song> getSongs() {
        return songs;
    }

    /**
     * Sets the list of songs in the music creation system.
     * 
     * @param songs The new list of songs.
     */
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    /**
     * Gets the list of sheet music available in the system.
     * 
     * @return The list of sheet music.
     */
    public List<SheetMusic> getSheetMusic() {
        return sheetMusic;
    }

    /**
     * Sets the list of sheet music in the system.
     * 
     * @param sheetMusic The new list of sheet music.
     */
    public void setSheetMusic(List<SheetMusic> sheetMusic) {
        this.sheetMusic = sheetMusic;
    }
}
