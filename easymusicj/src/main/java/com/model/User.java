package com.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String hashedPassword;
    private String email;
    private String firstName;
    private String lastName;
    private int practiceStreak = 0;
    private Instrument selectedInstrument;
    private List<Song> composedSongs = new ArrayList<>();

    public User(String username, String password, String email, String firstName, String lastName, int practiceStreak, Instrument selectedInstrument, List<Song> composedSongs) {
        this.username = username;
        this.hashedPassword = hashPassword(password); 
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.practiceStreak = 0;
        this.selectedInstrument = selectedInstrument;
        this.composedSongs = composedSongs;
    }

    public String getUsername() {
        return username;
    }

    /*
    * Password is hashed before stored for better security
    */
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public void updatePassword(String oldPassword, String newPassword) {
        if (hashedPassword.equals(hashPassword(oldPassword))) {
            this.hashedPassword = hashPassword(newPassword);
            System.out.println("Password updated successfully.");
        } else {
            System.out.println("Incorrect old password. Update failed.");
        }
    }

    public void createAccount() {
        System.out.println("Account created for: " + username);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void updateProfile(String newEmail, String newPassword) {
        this.email = newEmail;
        this.hashedPassword = hashPassword(newPassword); 
        System.out.println("Profile updated for: " + username);
    }

    public void updateEmail(String newEmail) {
        this.email = newEmail;
        System.out.println("Email updated for: " + username);
    }

    public void practice() {
        practiceStreak++;
        System.out.println(username + " practiced! Current streak: " + practiceStreak);
    }

    public void selectInstrument(Instrument instrument) {
        this.selectedInstrument = instrument;
        System.out.println(username + " selected " + instrument.getName() + " as their instrument.");
    }

    public Instrument getSelectedInstrument() {
        return selectedInstrument;
    }

    public void addComposedSong(Song song) {
        composedSongs.add(song);
        System.out.println("Song '" + song.getTitle() + "' added to " + username + "'s compositions.");
    }

    public List<Song> getComposedSongs() {
        return composedSongs;
    }
}
