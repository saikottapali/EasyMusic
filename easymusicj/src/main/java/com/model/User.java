package com.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    
    private UUID id;
    private String username;
    private String hashedPassword;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private int practiceStreak = 0;
    private Instrument selectedInstrument;
    private List<Song> composedSongs = new ArrayList<>();

    public User(UUID id, String username, String password, String email, String firstName, String lastName, 
    int practiceStreak, Instrument selectedInstrument, List<Song> composedSongs) {
        this.id = (id == null) ? UUID.randomUUID() : id; // Generate new UUID if null (i.e., new user)
        this.username = username;
        this.hashedPassword = hashPassword(password);
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.practiceStreak = 0;
        this.selectedInstrument = selectedInstrument;
        this.composedSongs = composedSongs;
    }

    /*
    *implemented password hashing for better security
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
    /*
    *Login gets verified
    */
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.hashedPassword.equals(hashPassword(password));
    }

    public void updateEmail(String newEmail) {
        this.email = newEmail;
        System.out.println("Email updated for: " + username);
    }

    public void updateProfile(String newEmail, String newPassword) {
        this.email = newEmail;
        this.password = newPassword;
        System.out.println("Profile updated for: " + username);
    }

    public void updatePassword(String oldPassword, String newPassword) {
        if (password.equals(hashPassword(oldPassword))) {
            this.hashedPassword = hashPassword(newPassword);
            System.out.println("Password updated successfully.");
        } else {
            System.out.println("Incorrect old password. Update failed.");
        }
    }

    public void practice() {
        practiceStreak++;
        System.out.println(username + " practiced! Current streak: " + practiceStreak);
    }

    public void selectInstrument(Instrument instrument) {
        this.selectedInstrument = instrument;
        System.out.println(username + " selected instrument: " + instrument.getName());
    }

    public void addComposedSong(Song song) {
        composedSongs.add(song);
        System.out.println("Song added: " + song.getTitle());
    }

    public String getUsername() {
        return username;
    }

    public void createAccount() {
        System.out.println("Account created for: " + username);
    }

    public void logIn() {
        System.out.println(username + " logged in.");
    }

    public void logOut() {
        System.out.println(username + " logged out.");
    }

    //Getters and Setters

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPracticeStreak() {
        return practiceStreak;
    }

    public void setPracticeStreak(int practiceStreak) {
        this.practiceStreak = practiceStreak;
    }

    public void setSelectedInstrument(Instrument selectedInstrument) {
        this.selectedInstrument = selectedInstrument;
    }

    public void setComposedSongs(List<Song> composedSongs) {
        this.composedSongs = composedSongs;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    
    
}
