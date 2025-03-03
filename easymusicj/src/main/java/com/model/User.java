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

    public void createAccount() {
        System.out.println("Account created for: " + username);
    }

    public void logIn() {
        System.out.println(username + " logged in.");
    }

    public boolean verifyPassword(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }

    public void selectInstrument(Instrument instrument) {
        this.selectedInstrument = instrument;
        System.out.println("Selected instrument: " + instrument.getName());
    }

    public void practice() {
        practiceStreak++;
        System.out.println("Practicing! Streak: " + practiceStreak);
    }
}
