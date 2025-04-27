package com.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MusicCreation {
    
    private List<SheetMusic> sheetMusic;   // List of sheet music available in the system
    /**
     * Constructor to initialize the music creation system with empty lists for instruments, songs, and sheet music.
     */
    public MusicCreation() {
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
     * @param songNotes List of notes related to the song.
     * @return The newly created song.
     */
    public Song createMusic(String title, String composer, String difficultyLevel, Date date, boolean isPrivate, SheetMusic sheetMusic, List<Note> songNotes) {
        // Generate UUID for the new song
        UUID id = UUID.randomUUID();

        // Create a new song object
        List<Song> newSong = (List<Song>) new Song(id, title, composer, difficultyLevel, sheetMusic, isPrivate, songNotes);
        // Save the song to a platform (like a database or list)
        return (Song) newSong;
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
     * @param note The note to play.
     * @param musicLibrary The music library containing the songs.
     */
    public void playSong(Song song, Note note) {
        if (song.getSheetMusic() == null || song.getSheetMusic().getJFugueNotation() == null || song.getSheetMusic().getJFugueNotation().isEmpty()) {
            System.out.println("No sheet music available for this song.");
        } else {
            System.out.println("Attempting to play song: " + song.getTitle());
            note.play();
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
