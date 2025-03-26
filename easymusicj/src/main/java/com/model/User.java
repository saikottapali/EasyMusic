package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    
    private UUID id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private int practiceStreak = 0;
    private Instrument selectedInstrument;
    private List<Song> composedSongs = new ArrayList<>();
    private boolean isLoggedIn = false;
    
    public User(UUID id, String username, String password, String email, String firstName, String lastName, 
    int practiceStreak, Instrument selectedInstrument, List<Song> composedSongs, boolean isLoggedIn) {
        this.id = (id == null) ? UUID.randomUUID() : id; // Generate new UUID if null (i.e., new user)
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.practiceStreak = 0;
        this.selectedInstrument = selectedInstrument;
        this.composedSongs = composedSongs;
        this.isLoggedIn = false;
    }
    
    public void practice() {
        if (isLoggedIn) {
            practiceStreak++;
        }
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    public boolean logIn(String enteredPassword) {
        if (this.password.equals(enteredPassword)){
            isLoggedIn = true;
            return true; 
        }
        return false;
    }
    
    public void logOut(){
        isLoggedIn = false;
    }
    
    public void selectInstrument(Instrument instrument) {
        this.selectedInstrument = instrument;
    }
    
    public Instrument getSelectedInstrument() {
        return selectedInstrument;
    }
    
    public void addComposedSong(Song song) {
        composedSongs.add(song);
    }
    
    public List<Song> getComposedSongs() {
        return composedSongs;
    }

    public void updateProfile(String newEmail, String newPassword) {
        this.email = newEmail;
        this.password = newPassword;
    }
        
        //Getters and Setters
    
    public void setUsername(String username) {
        this.username = username;
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

    public String getUsername() {
        return username;
    }
}
