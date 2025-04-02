package com.model;

import java.util.List;

public class MusicLibrary {
    private static MusicLibrary instance;
    private List<Song> songs;  

    public MusicLibrary() {
        songs = DataLoader.loadSongs(); // Load songs from DataLoader
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

    public List<Song> getSongs() {{
        return songs;
    }}

}
