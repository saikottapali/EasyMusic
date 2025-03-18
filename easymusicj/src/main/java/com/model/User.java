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

    public User(String username, String password, String email, String firstName, String lastName, int practiceStreak, Instrument selectedInstrument, List<Song> composedSongs) {
        this.username = username;
        this.password = password;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Instrument getSelectedInstrument() {
        return selectedInstrument;
    }

    public void setSelectedInstrument(Instrument selectedInstrument) {
        this.selectedInstrument = selectedInstrument;
    }

    public List<Song> getComposedSongs() {
        return composedSongs;
    }

    public void setComposedSongs(List<Song> composedSongs) {
        this.composedSongs = composedSongs;
    }

    
}
