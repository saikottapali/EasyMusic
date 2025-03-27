package com.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The MusicLibrary class manages a collection of songs. It allows for adding, searching, 
 * and retrieving all songs in the library.
 */
public class MusicLibrary {
    private List<Song> allSongs;

    /**
     * Constructor that initializes the MusicLibrary with an empty list of songs.
     */
    public MusicLibrary() {
        this.allSongs = new ArrayList<>();
    }

    /**
     * Adds a song to the music library.
     * 
     * @param song The song to be added to the library.
     */
    public void addSong(Song song) {
        allSongs.add(song);
        System.out.println("Song added to library: " + song.getTitle());
    }

    /**
     * Searches for a song in the library by its title.
     * 
     * @param title The title of the song to search for.
     * @return The song if found, otherwise null.
     */
    public Song searchSong(String title) {
        for (Song song : allSongs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                return song;
            }
        }
        System.out.println("Song not found: " + title);
        return null;
    }

    /**
     * Gets a list of all songs in the music library.
     * 
     * @return A new list containing all songs in the library.
     */
    public List<Song> getAllSongs() {
        return new ArrayList<>(allSongs);
    }
}
