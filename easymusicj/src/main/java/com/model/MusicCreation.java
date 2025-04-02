package com.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MusicCreation {
    private List<Instrument> instruments;  // List of available instruments
    private List<Song> songs;              // List of songs in the system
    private List<SheetMusic> sheetMusic;   // List of sheet music available

    /**
     * Constructor to initialize the music creation system with empty lists for instruments, songs, and sheet music.
     */
    public MusicCreation() {
        this.instruments = new ArrayList<>();
        this.songs = new ArrayList<>();
        this.sheetMusic = new ArrayList<>();
    }

    /**
     * Creates a new song and adds it to the list of songs.
     * 
     * @param id The ID of the new song.
     * @param title The title of the song.
     * @param composer The composer of the song.
     * @param difficultyLevel The difficulty level of the song.
     * @param date The date the song was created.
     * @param sheetMusic The sheet music associated with the song.
     * @param isPrivate Whether the song is private or public.
     * @param comments List of comments for the song.
     * @param songNotes List of notes related to the song.
     * @param loggedIn The login status of the user (not used in this method).
     * @param tags Tags associated with the song.
     * @return The newly created song.
     */
    public Song createMusic(String title, String composer, String difficultyLevel, Date date, 
    boolean isPrivate, ArrayList<String> songNotes, SheetMusic sheetMusic) {
        // Generate UUID for the new song
        String id = UUID.randomUUID().toString();

        // Create a new song object
        Song newSong = new Song(id, title, composer, sheetMusic, isPrivate, songNotes);
        // Save the song to a platform (like a database or list)
        sheetMusic.saveToFile(newSong);

        return newSong;
    }

    /**
     * Uploads a song if it exists in the list of songs.
     * 
     * @param song The song to upload.
     * @return True if the song is found and uploaded, false if the song is not found.
     */

    /**
     * Plays a song if it exists in the list of songs.
     * 
     * @param song The song to play.
     */
    public void playSong(Song song, Note note) {
        if (songs.contains(song)) {
            System.out.println("Attempting to play song: " + song.getTitle());
            
            // Check if the song has valid sheet music
            if (song.getSheetMusic() == null || song.getSheetMusic().getJFugueNotation().isEmpty()) {
                System.out.println("No sheet music available for this song.");
            } else {
                note.play();  // Assuming Song has a play method to play the song
            }
        } else {
            System.out.println("Song not found, cannot play.");
        }
    }

    // Getter and Setter methods for sheet music

    public List<SheetMusic> getSheetMusic() {
        return sheetMusic;
    }

    public void setSheetMusic(List<SheetMusic> sheetMusic) {
        this.sheetMusic = sheetMusic;
    }
}
