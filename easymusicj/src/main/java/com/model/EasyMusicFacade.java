package com.model;

import java.util.ArrayList;
import java.util.Date;

public class EasyMusicFacade {
    private User user;                  // The logged-in user
    private MusicCreation musicPost;    // The music creation platform (handles song creation, saving, etc.)
    private Song selectedSong;          // The selected song for playback, upload, or download
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
    public boolean logIn(String username, String password) {
        if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    /**
     * Logs out the user.
     */
    public void logOut() {
        user = null;  // Sets the user to null to indicate the user is logged out
    }

    public String viewProfile() {
        if(user != null) {
            return user.toString();
        }
        return null;
    }

    /**
     * Allows the user to select a song by its title.
     *
     * @param songTitle The title of the song to select.
     * @return The selected song, or null if the song is not found.
     */
    public boolean chooseSong(String songTitle) {
        selectedSong = musicPost.searchSong(songTitle);
        return selectedSong != null;
    }

    /**
     * Views the sheet music of a selected song.
     *
     * @param songTitle The title of the song to view sheet music for.
     * @return The sheet music for the song, or null if the song is not found.
     */
    public String viewSheetMusic(String songTitle) {
        Song song = musicPost.searchSong(songTitle);
        if (song != null) {
            return song.getSheetMusic().toString();
        }
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
     * Saves the selected song to the music platform.
     *
     * @param song The song to save.
     * @return True if the song was saved successfully.
     */
    public boolean saveMusic() {
        if (selectedSong != null) {
            return musicPost.saveMusic(selectedSong);
      }  
        return false;  // Return false if no song is selected
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
     * @return The created song.
     */
    public boolean createMusic(String title, String difficultyLevel, boolean isPrivate, 
    SheetMusic sheetMusic, ArrayList<String> songNotes) {
        if(user != null && user.isLoggedIn()) {
            String composer = user.getFirstName() + " " + user.getLastName();
            Song newSong = musicPost.createMusic(title, composer, difficultyLevel, 
            new Date(), isPrivate, songNotes, sheetMusic);
            return newSong != null;
        }
        return false;
    }
    /**
     * Plays the currently selected song.
     */
    public boolean playSelectedSong() {
        if (selectedSong != null) {
            selectedSong.play();
            return true;  
        }
        return false;
    }

    /**
     * Uploads the currently selected song to the platform.
     * Displays a message if no song is selected.
     */
    public boolean uploadSelectedSong() {
        if (selectedSong != null) {
            selectedSong.upload();
        } 
        return false;
        
    }

    /**
     * Downloads the currently selected song from the platform.
     * Displays a message if no song is selected.
     */
    public boolean downloadSelectedSong() {
        if (selectedSong != null) {
            selectedSong.download();  // Assuming Song has a download method
        }
        return false;
    }
}
