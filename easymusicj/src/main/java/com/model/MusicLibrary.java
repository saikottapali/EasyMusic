package com.model;

import java.util.ArrayList;
import java.util.List;

public class MusicLibrary {
    
    private List<Song> songs;  // List to store all the songs in the library

    public MusicLibrary() {
        this.songs = new ArrayList<>();
    }

    // Method to add a song to the library
    public void addSong(Song song) {
        songs.add(song);
        System.out.println("Song added: " + song.getTitle());
    }

    // Method to remove a song from the library
    public boolean removeSong(Song song) {
        if (songs.contains(song)) {
            songs.remove(song);
            System.out.println("Song removed: " + song.getTitle());
            return true;
        }
        System.out.println("Song not found in library.");
        return false;
    }

    // Method to search for a song by its title
    public Song searchSongByTitle(String title) {
        for (Song song : songs) {
            if (song.getTitle().equals(title)) {
                System.out.println("Song found: " + title);
                return song;
            }
        }
        System.out.println("Song not found: " + title);
        return null;
    }

    // Method to get all songs in the library
    public List<Song> getSongs() {
        return songs;
    }
}
