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

    // Search for songs by artist
    public List<Song> searchSongsByArtist(String artist) {
    List<Song> result = new ArrayList<>();
    for (Song song : allSongs) {
        if (song.getComposer() != null && song.getComposer().equalsIgnoreCase(artist)) {
            result.add(song);
        }
    }
    return result;
}


    // Get a list of all songs
    public List<Song> getAllSongs() {
        return new ArrayList<>(allSongs);
    }
}
