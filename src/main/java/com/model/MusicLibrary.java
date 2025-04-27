package com.model;

import java.util.ArrayList;
import java.util.List;

public class MusicLibrary {

    private static MusicLibrary instance;
    private List<Song> songs;  

    public MusicLibrary() {
        songs = DataLoader.loadSongs(); // Load songs from DataLoader
        if (songs == null) {
            songs = new ArrayList<>(); // Initialize with an empty list if loading fails
            System.out.println("Failed to load songs from file. Initialized with an empty list.");
        }
    }

    public static MusicLibrary getInstance() {
        if (instance == null) {
            instance = new MusicLibrary();
        }
        return instance;
    }

    public void addSong(Song song) {
        songs.add(song);
        DataWriter.saveSongs(songs); // Save songs to file
    }

    public void removeSong(Song song) {
        songs.remove(song);
        DataWriter.saveSongs(songs); // Remove Songs from file
    }

    public List<Song> getSongs() {
        return songs;
    }

}
