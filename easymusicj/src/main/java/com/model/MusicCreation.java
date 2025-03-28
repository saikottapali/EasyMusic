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
    public Song createMusic(UUID id, String title, String composer, String difficultyLevel, Date date, 
    SheetMusic sheetMusic, boolean isPrivate, List<String> comments, List<String> songNotes, boolean loggedIn, List<String> tags) {
        // Create a new song and add it to the songs list
        Song newSong = new Song(id, title, composer, sheetMusic, isPrivate, new ArrayList<>(songNotes));
        songs.add(newSong);
        System.out.println("New song created: " + title);
        return newSong;
    }

    /**
     * Saves a song if it exists in the list of songs.
     * 
     * @param song The song to save.
     * @return True if the song is found and saved, false if the song is not found.
     */
    public boolean saveMusic(Song song) {
        if (songs.contains(song)) {
            System.out.println("Music saved: " + song.getTitle());
            return true;
        }
        System.out.println("Song not found, save failed. Current songs in the list: ");
        for (Song s : songs) {
            System.out.println(s.getTitle());
        }
        return false;
   }

    /**
     * Uploads a song if it exists in the list of songs.
     * 
     * @param song The song to upload.
     * @return True if the song is found and uploaded, false if the song is not found.
     */
    public boolean uploadSong(Song song) {
        if (songs.contains(song)) {
            song.upload();  // Assuming Song has an upload method to upload the song
            return true;
        }
        System.out.println("Song not found, upload failed.");
        return false;
    }

    /**
     * Plays a song if it exists in the list of songs.
     * 
     * @param song The song to play.
     */
    public void playSong(Song song) {
        if (songs.contains(song)) {
            System.out.println("Attempting to play song: " + song.getTitle());
            
            // Check if the song has valid sheet music
            if (song.getSheetMusic() == null || song.getSheetMusic().getJFugueNotation().isEmpty()) {
                System.out.println("No sheet music available for this song.");
            } else {
                song.play();  // Assuming Song has a play method to play the song
            }
        } else {
            System.out.println("Song not found, cannot play.");
        }
    }

    /**
     * Downloads a song if it exists in the list of songs.
     * 
     * @param song The song to download.
     * @return True if the song is found and downloaded, false if the song is not found.
     */
    public boolean downloadSong(Song song) {
        if (songs.contains(song)) {
            song.download();  // Assuming Song has a download method to download the song
            return true;
        }
        System.out.println("Song not found, download failed.");
        return false;
    }

    /**
     * Searches for a song by its title.
     * 
     * @param title The title of the song to search for.
     * @return The song with the given title, or null if no such song exists.
     */
    public Song searchSong(String title) {
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                return song;
            }
        }
        System.out.println("Song with title '" + title + "' not found.");
        return null;
    }

    // Getter and Setter methods for instruments, songs, and sheet music

    public List<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<SheetMusic> getSheetMusic() {
        return sheetMusic;
    }

    public void setSheetMusic(List<SheetMusic> sheetMusic) {
        this.sheetMusic = sheetMusic;
    }
}
