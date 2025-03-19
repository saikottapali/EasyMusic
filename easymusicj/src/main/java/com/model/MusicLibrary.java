package com.model;

import java.util.ArrayList;
import java.util.List;

public class MusicLibrary {
    private List<Song> allSongs;

    public MusicLibrary() {
        this.allSongs = new ArrayList<>();
    }

    public void addSong(Song song) {
        allSongs.add(song);
        System.out.println("Song added to library: " + song.getTitle());
    }

    // Search for a song by title
    public Song searchSong(String title) {
        for (Song song : allSongs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                return song;
            }
        }
        System.out.println("Song not found: " + title);
        return null;
    }

    // Get a list of all songs
    public List<Song> getAllSongs() {
        return new ArrayList<>(allSongs);
    }
}
