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

    public String getUsername() {
        return username;
    }

    public void createAccount() {
        System.out.println("Account created for: " + username);
    }

    public void updateProfile(String newEmail, String newPassword) {
        this.email = newEmail;
        this.password = newPassword;
        System.out.println("Profile updated for: " + username);
    }

    public void practice() {
        practiceStreak++;
        System.out.println(username + " practiced! Current streak: " + practiceStreak);
    }
}
