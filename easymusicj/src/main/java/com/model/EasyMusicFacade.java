package com.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * The EasyMusicFacade class provides a simplified interface for interacting with 
 * various music-related actions such as logging in, selecting instruments, choosing songs, 
 * viewing sheet music, playing notes, recording music, and sharing music.
 * This facade acts as a bridge between the user and the underlying music creation system.
 */
public class EasyMusicFacade {
    private User user;
    private MusicCreation musicPost;
    private Song selectedSong;
    private Instrument selectedInstrument;

    /**
     * Constructor for EasyMusicFacade that initializes the user and music creation object.
     * 
     * @param user The user interacting with the system.
     * @param musicPost The music creation object used to manage music-related actions.
     */
    public EasyMusicFacade(User user, MusicCreation musicPost) {
        this.user = user;
        this.musicPost = musicPost;
    }

    /**
     * Logs in a user using their username and password.
     * 
     * @param username The username of the user trying to log in.
     * @param password The password of the user.
     */
    public void logIn(String username, String password) {
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            System.out.println("Login successful for: " + username);
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    /**
     * Logs out the current user.
     */
    public void logOut() {
        System.out.println("Logged out successfully.");
        user = null;
    }

    /**
     * Selects an instrument from the available instruments. If the instrument is found, 
     * it is set as the selected instrument; otherwise, a "not found" message is displayed.
     * 
     * @param instrumentName The name of the instrument to select.
     * @return The selected instrument, or null if not found.
     */
    public Instrument chooseInstrument(String instrumentName) {
        for (Instrument instrument : musicPost.getInstruments()) {
            if (instrument.getName().equalsIgnoreCase(instrumentName)) {
                selectedInstrument = instrument;
                System.out.println("Selected instrument: " + instrumentName);
                return selectedInstrument;
            }
        }
        System.out.println("Instrument not found.");
        return null;
    }

    /**
     * Selects a song by its title. If the song is found, it is set as the selected song;
     * otherwise, a "not found" message is displayed.
     * 
     * @param songTitle The title of the song to select.
     * @return The selected song, or null if not found.
     */
    public Song chooseSong(String songTitle) {
        selectedSong = musicPost.searchSong(songTitle);
        if (selectedSong != null) {
            System.out.println("Selected song: " + songTitle);
        } else {
            System.out.println("Song not found.");
        }
        return selectedSong;
    }

    /**
     * Views the sheet music of a selected song.
     * 
     * @param songTitle The title of the song whose sheet music is to be viewed.
     * @return The sheet music of the selected song, or null if the song is not found.
     */
    public SheetMusic viewSheetMusic(String songTitle) {
        Song song = musicPost.searchSong(songTitle);
        if (song != null) {
            return song.getSheetMusic();
        }
        System.out.println("Sheet music not found.");
        return null;
    }

    /**
     * Plays a given note.
     * 
     * @param note The note to be played.
     */
    public void playNote(Note note) {
        note.play();
    }

    /**
     * Starts recording music.
     * 
     * @return true if recording started successfully.
     */
    public boolean recordMusic() {
        System.out.println("Recording started...");
        return true;
    }

    /**
     * Saves a song to the music post system.
     * 
     * @param song The song to be saved.
     * @return true if the song was saved successfully.
     */
    public boolean saveMusic(Song song) {
        return musicPost.saveMusic(song);
    }

    /**
     * Shares the music publicly.
     * 
     * @return true if the music was shared successfully.
     */
    public boolean shareMusic() {
        System.out.println("Music shared successfully!");
        return true;
    }

    /**
     * Views the profile of the current user.
     * 
     * @return The current user.
     */
    public User viewProfile() {
        System.out.println("Profile for: " + user.getUsername());
        return user;
    }

    /**
     * Creates a new song with the provided details and the selected instrument.
     * 
     * @param id The unique identifier for the song.
     * @param title The title of the song.
     * @param composer The composer of the song.
     * @param difficultyLevel The difficulty level of the song.
     * @param date The creation date of the song.
     * @param sheetMusic The sheet music associated with the song.
     * @param isPrivate Whether the song is private or public.
     * @param comments A list of comments associated with the song.
     * @return The newly created song.
     */
    public Song createMusic(UUID id, String title, String composer, String difficultyLevel, 
        Date date, SheetMusic sheetMusic, boolean isPrivate, List<String> comments) {

        return musicPost.createMusic(id, title, composer, difficultyLevel, date, 
                sheetMusic, isPrivate, comments, selectedInstrument, comments, isPrivate, comments);
    }

    /**
     * Plays the currently selected song.
     */
    public void playSelectedSong() {
        if (selectedSong != null) {
            selectedSong.play();
        } else {
            System.out.println("No song selected.");
        }
    }

    /**
     * Uploads the currently selected song.
     */
    public void uploadSelectedSong() {
        if (selectedSong != null) {
            selectedSong.upload();
        } else {
            System.out.println("No song selected to upload.");
        }
    }

    /**
     * Downloads the currently selected song.
     */
    public void downloadSelectedSong() {
        if (selectedSong != null) {
            selectedSong.download();
        } else {
            System.out.println("No song selected to download.");
        }
    }
}
