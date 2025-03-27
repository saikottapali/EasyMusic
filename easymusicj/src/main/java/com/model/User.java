package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The {@code User} class represents a user in the music learning app.
 * It contains user-related details such as their personal information, 
 * login status, composed songs, and practice streak.
 */
public class User {

    private UUID id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private int practiceStreak = 0;
    private List<Song> composedSongs = new ArrayList<>();
    private boolean isLoggedIn = false;

    /**
     * Constructor for the {@code User} class.
     * 
     * @param id The unique identifier for the user.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param email The email address of the user.
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param practiceStreak The current practice streak of the user.
     * @param composedSongs The list of songs composed by the user.
     * @param isLoggedIn The login status of the user.
     */
    public User(UUID id, String username, String password, String email, 
                String firstName, String lastName, int practiceStreak, 
                List<Song> composedSongs, boolean isLoggedIn) {
        this.id = (id == null) ? UUID.randomUUID() : id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.practiceStreak = practiceStreak;
        this.composedSongs = (composedSongs != null) ? composedSongs : new ArrayList<>();
        this.isLoggedIn = isLoggedIn;
    }

    /**
     * Retrieves the user's password (not hashed).
     * 
     * @return The user's password.
     */
    public String getHashedPassword() {
        return password;
    }
    
    /**
     * Increases the user's practice streak by one if the user is logged in.
     */
    public void practice() {
        if (isLoggedIn) {
            practiceStreak++;
        }
    }
    
    /**
     * Retrieves the user's password.
     * 
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     * 
     * @param password The new password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the user's login status.
     * 
     * @param isLoggedIn The login status to set.
     */
    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    /**
     * Checks if the user is currently logged in.
     * 
     * @return {@code true} if the user is logged in, {@code false} otherwise.
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    /**
     * Logs the user in by verifying the entered password.
     * 
     * @param enteredPassword The password entered by the user.
     * @return {@code true} if the entered password matches the user's password, {@code false} otherwise.
     */
    public boolean logIn(String enteredPassword) {
        if (this.password.equals(enteredPassword)){
            isLoggedIn = true;
            return true; 
        }
        return false;
    }
    
    /**
     * Logs the user out.
     */
    public void logOut(){
        isLoggedIn = false;
    }
    
    /**
     * Adds a composed song to the user's list of composed songs.
     * 
     * @param song The song to add.
     */
    public void addComposedSong(Song song) {
        composedSongs.add(song);
    }
    
    /**
     * Retrieves the list of songs composed by the user.
     * 
     * @return The list of composed songs.
     */
    public List<Song> getComposedSongs() {
        return composedSongs;
    }

    /**
     * Updates the user's profile with a new email and password.
     * 
     * @param newEmail The new email to set.
     * @param newPassword The new password to set.
     */
    public void updateProfile(String newEmail, String newPassword) {
        this.email = newEmail;
        this.password = newPassword;
    }
        
    // Getters and Setters

    /**
     * Sets the user's username.
     * 
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * Retrieves the user's email.
     * 
     * @return The user's email.
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Sets the user's email.
     * 
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Retrieves the user's first name.
     * 
     * @return The user's first name.
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Sets the user's first name.
     * 
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * Retrieves the user's last name.
     * 
     * @return The user's last name.
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Sets the user's last name.
     * 
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * Retrieves the user's practice streak.
     * 
     * @return The user's practice streak.
     */
    public int getPracticeStreak() {
        return practiceStreak;
    }
    
    /**
     * Sets the user's practice streak.
     * 
     * @param practiceStreak The practice streak to set.
     */
    public void setPracticeStreak(int practiceStreak) {
        this.practiceStreak = practiceStreak;
    }
    
    /**
     * Sets the list of songs composed by the user.
     * 
     * @param composedSongs The list of composed songs to set.
     */
    public void setComposedSongs(List<Song> composedSongs) {
        this.composedSongs = composedSongs;
    }

    /**
     * Retrieves the unique identifier of the user.
     * 
     * @return The user's UUID.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     * 
     * @param id The UUID to set.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Retrieves the user's username.
     * 
     * @return The user's username.
     */
    public String getUsername() {
        return username;
    }
}
