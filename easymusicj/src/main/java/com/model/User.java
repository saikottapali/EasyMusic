package com.model;

import java.util.List;

public class User {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private int practiceStreak = 0;
    private Instrument selectedInstrument;
    private List<Song> composedSongs;

    
    public User(String username, String password, String email, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void logIn() {
        System.out.println(username + " logged in.");
    }

    public void logOut() {
        System.out.println(username + " logged out.");
    }

    public void chooseInstrument(Instrument instrument) {
        this.selectedInstrument = instrument;
        System.out.println(username + " select instrument: " + instrument.getName());
    }

    public void chooseSong(Song song) {
        composedSongs.add(song);
        System.out.println(username + " selected song: " + song.getTitle());
    }
}
