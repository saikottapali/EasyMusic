package com.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class EasyMusicFacade {
    private User user;
    private MusicCreation musicPost;
    private Song selectedSong;
    private Instrument selectedInstrument;

    public EasyMusicFacade(User user, MusicCreation musicPost) {
        this.user = user;
        this.musicPost = musicPost;
    }

    //Log In uses Username and Password credentials
    public void logIn(String username, String password) {
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            System.out.println("Login successful for: " + username);
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    public void logOut() {
        System.out.println("Logged out successfully.");
        user = null;
    }

    //Selects Instrument, will return 'not found' if instrument isn't available
    public Instrument chooseInstrument(String instrumentName) {
        for (Instrument instrument : musicPost.getInstruments()) {
            if (instrument.getName().equalsIgnoreCase(instrumentName)) {
                selectedInstrument = instrument;
                System.out.println("Selected instrument: " + instrumentName);
                return selectedInstrument;
            }
        }
        System.out.println("Instrument not found.");
        return null;
    }

    public Song chooseSong(String songTitle) {
        selectedSong = musicPost.searchSong(songTitle);
        if (selectedSong != null) {
            System.out.println("Selected song: " + songTitle);
        } else {
            System.out.println("Song not found.");
        }
        return selectedSong;
    }

    public SheetMusic viewSheetMusic(String songTitle) {
        Song song = musicPost.searchSong(songTitle);
        if (song != null) {
            return song.getSheetMusic();
        }
        System.out.println("Sheet music not found.");
        return null;
    }

    public void playNote(Note note) {
        note.play();
    }

    public boolean recordMusic() {
        System.out.println("Recording started...");
        return true;
    }

    public boolean saveMusic(Song song) {
        return musicPost.saveMusic(song);
    }

    public boolean shareMusic() {
        System.out.println("Music shared successfully!");
        return true;
    }

    public User viewProfile() {
        System.out.println("Profile for: " + user.getUsername());
        return user;
    }

    public Song createMusic(UUID id, String title, String composer, String difficultyLevel, 
        Date date, SheetMusic sheetMusic, boolean isPrivate, List<String> comments) {

        return musicPost.createMusic(id, title, composer, difficultyLevel, date, 
                sheetMusic, isPrivate, comments, comments, isPrivate, comments);
        
    }

    public void playSelectedSong() {
        if (selectedSong != null) {
            selectedSong.play();
        } else {
            System.out.println("No song selected.");
        }
    }

    public void uploadSelectedSong() {
        if (selectedSong != null) {
            selectedSong.upload();
        } else {
            System.out.println("No song selected to upload.");
        }
    }

    public void downloadSelectedSong() {
        if (selectedSong != null) {
            selectedSong.download();
        } else {
            System.out.println("No song selected to download.");
        }
    }
}
