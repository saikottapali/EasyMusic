package com.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MusicCreation {
    private List<Instrument> instruments;
    private List<Song> songs;
    private List<SheetMusic> sheetMusic;

    public MusicCreation() {
        this.instruments = new ArrayList<>();
        this.songs = new ArrayList<>();
        this.sheetMusic = new ArrayList<>();
    }

    public Song createMusic(String title, String composer, String difficultyLevel, Date date, 
    SheetMusic sheetMusic, boolean isPrivate, List<String> comments, Instrument instrument, List<String> songNotes) {
        Song newSong = new Song(title, composer, difficultyLevel, instrument, date, sheetMusic, 
        isPrivate, isPrivate, comments, songNotes);
        songs.add(newSong);
        System.out.println("New song created: " + title);
        return newSong; 
    }


    public boolean saveMusic(Song song) {
        if (songs.contains(song)) {
            System.out.println("Music saved: " + song.getTitle());
            return true;
        }
        System.out.println("Song not found, save failed.");
        return false;
    }

    public boolean uploadSong(Song song) {
        if (songs.contains(song)) {
            song.upload();
            return true;
        }
        System.out.println("Song not found, upload failed.");
        return false;
    }

    public void playSong(Song song) {
        if (songs.contains(song)) {
            song.play();
        } else {
            System.out.println("Song not found, cannot play.");
        }
    }

    public boolean downloadSong(Song song) {
        if (songs.contains(song)) {
            song.download();
            return true;
        }
        System.out.println("Song not found, download failed.");
        return false;
    }

    public Song searchSong(String title) {
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                return song;
            }
        }
        return null;
    }

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
