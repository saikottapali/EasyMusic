package com.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class EasyMusicFacade {
    private User user;                  // The logged-in user
    private MusicCreation musicPost;    // The music creation platform (handles song creation, saving, etc.)
    private Song selectedSong;          // The selected song for playback, upload, or download
    private Instrument selectedInstrument; // The selected instrument for the user

    /**
     * Constructor for EasyMusicFacade.
     * Initializes the facade with the logged-in user and the music creation platform.
     *
     * @param user The logged-in user.
     * @param musicPost The music creation platform.
     */
    public EasyMusicFacade(User user, MusicCreation musicPost) {
        this.user = user;
        this.musicPost = musicPost;
    }

    /**
     * Logs in the user using the provided username and password.
     *
     * @param username The username.
     * @param password The password.
     */
    public void logIn(String username, String password) {
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            System.out.println("Login successful for: " + username);
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    /**
     * Logs out the user.
     */
    public void logOut() {
        System.out.println("Logged out successfully.");
        user = null;  // Sets the user to null to indicate the user is logged out
    }

    /**
     * Allows the user to select an instrument by name.
     * Returns 'null' if the instrument is not found.
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
     * Allows the user to select a song by its title.
     *
     * @param songTitle The title of the song to select.
     * @return The selected song, or null if the song is not found.
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
     * @param songTitle The title of the song to view sheet music for.
     * @return The sheet music for the song, or null if the song is not found.
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
     * @param note The note to play.
     */
    public void playNote(Note note) {
        note.play();  // Assuming Note has a play method to play the note's sound
    }

    /**
     * Starts recording music.
     *
     * @return True if recording is started successfully.
     */
    public boolean recordMusic() {
        System.out.println("Recording started...");
        return true;
    }

    /**
     * Saves the selected song to the music platform.
     *
     * @param song The song to save.
     * @return True if the song was saved successfully.
     */
    public boolean saveMusic(Song song) {
        return musicPost.saveMusic(song);  // Assuming musicPost handles saving the song
    }

    /**
     * Shares the music on the platform.
     *
     * @return True if the music was shared successfully.
     */
    public boolean shareMusic() {
        System.out.println("Music shared successfully!");
        return true;
    }

    /**
     * Views the profile of the logged-in user.
     *
     * @return The user's profile information.
     */
    public User viewProfile() {
        System.out.println("Profile for: " + user.getUsername());
        return user;
    }

    /**
     * Creates a new song with the given details and returns the created song.
     *
     * @param id The ID of the song.
     * @param title The title of the song.
     * @param composer The composer of the song.
     * @param difficultyLevel The difficulty level of the song.
     * @param date The date of creation.
     * @param sheetMusic The sheet music for the song.
     * @param isPrivate Whether the song is private or public.
     * @param comments A list of comments on the song.
     * @return The created song.
     */
    public Song createMusic(UUID id, String title, String composer, String difficultyLevel, 
        Date date, SheetMusic sheetMusic, boolean isPrivate, List<String> comments) {

        return musicPost.createMusic(id, title, composer, difficultyLevel, date, 
                sheetMusic, isPrivate, comments, comments, isPrivate, comments);  // Assuming musicPost handles song creation
    }

    /**
     * Plays the currently selected song.
     * Displays a message if no song is selected.
     */
    public void playSelectedSong() {
        if (selectedSong != null) {
            selectedSong.play();  // Assuming Song has a play method
        } else {
            System.out.println("No song selected.");
        }
    }

    /**
     * Uploads the currently selected song to the platform.
     * Displays a message if no song is selected.
     */
    public void uploadSelectedSong() {
        if (selectedSong != null) {
            selectedSong.upload();  // Assuming Song has an upload method
        } else {
            System.out.println("No song selected to upload.");
        }
    }

    /**
     * Downloads the currently selected song from the platform.
     * Displays a message if no song is selected.
     */
    public void downloadSelectedSong() {
        if (selectedSong != null) {
            selectedSong.download();  // Assuming Song has a download method
        } else {
            System.out.println("No song selected to download.");
        }
    }
}
