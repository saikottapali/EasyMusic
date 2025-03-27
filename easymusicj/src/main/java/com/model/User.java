package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a user in the music application. The user has attributes such as
 * their personal details (username, password, email, name), practice streak,
 * selected instrument, and a list of composed songs. The class also provides 
 * functionality for logging in, practicing, updating the profile, and managing 
 * the user's composed songs.
 */
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
    
    /**
     * Constructs a new User object with the given details. If the id is null,
     * a new UUID is generated.
     *
     * @param id The unique identifier for the user.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param email The email address of the user.
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param practiceStreak The number of consecutive practice days the user has.
     * @param selectedInstrument The instrument selected by the user.
     * @param composedSongs The list of songs composed by the user.
     * @param b A boolean value indicating the login status of the user.
     */
    public User(UUID id, String username, String password, String email, String firstName, String lastName, 
                int practiceStreak, Instrument selectedInstrument, List<Song> composedSongs, boolean b) {
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

    /**
     * Increases the user's practice streak if the user is logged in.
     * The practice streak is incremented by 1 for each practice session.
     */
    public void practice() {
        if (isLoggedIn) {
            practiceStreak++;
        }
    }

    /**
     * Gets the user's password.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets a new password for the user.
     *
     * @param password The new password for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the login status of the user.
     *
     * @param isLoggedIn True if the user is logged in, false otherwise.
     */
    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    /**
     * Checks if the user is logged in.
     *
     * @return True if the user is logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    /**
     * Attempts to log in the user by checking the entered password.
     *
     * @param enteredPassword The password entered by the user.
     * @return True if the password matches the user's stored password, false otherwise.
     */
    public boolean logIn(String enteredPassword) {
        if (this.password.equals(enteredPassword)){
            isLoggedIn = true;
            return true; 
        }
        return false;
    }

    /**
     * Logs out the user by setting the login status to false.
     */
    public void logOut(){
        isLoggedIn = false;
    }

    /**
     * Sets the instrument selected by the user.
     *
     * @param instrument The instrument selected by the user.
     */
    public void selectInstrument(Instrument instrument) {
        this.selectedInstrument = instrument;
    }

    /**
     * Gets the instrument selected by the user.
     *
     * @return The instrument selected by the user.
     */
    public Instrument getSelectedInstrument() {
        return selectedInstrument;
    }

    /**
     * Adds a composed song to the user's list of songs.
     *
     * @param song The song to be added to the list of composed songs.
     */
    public void addComposedSong(Song song) {
        composedSongs.add(song);
    }

    /**
     * Gets the list of songs composed by the user.
     *
     * @return A list of songs composed by the user.
     */
    public List<Song> getComposedSongs() {
        return composedSongs;
    }

    /**
     * Updates the user's profile with a new email and password.
     *
     * @param newEmail The new email address for the user.
     * @param newPassword The new password for the user.
     */
    public void updateProfile(String newEmail, String newPassword) {
        this.email = newEmail;
        this.password = newPassword;
    }

    // Getters and Setters

    /**
     * Sets the username for the user.
     *
     * @param username The new username for the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the email address of the user.
     *
     * @return The email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address for the user.
     *
     * @param email The new email address for the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the first name of the user.
     *
     * @return The first name of the user.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name for the user.
     *
     * @param firstName The new first name for the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the user.
     *
     * @return The last name of the user.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name for the user.
     *
     * @param lastName The new last name for the user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the user's practice streak.
     *
     * @return The number of consecutive practice days the user has.
     */
    public int getPracticeStreak() {
        return practiceStreak;
    }

    /**
     * Sets the practice streak for the user.
     *
     * @param practiceStreak The new practice streak for the user.
     */
    public void setPracticeStreak(int practiceStreak) {
        this.practiceStreak = practiceStreak;
    }

    /**
     * Sets the instrument for the user.
     *
     * @param instrument The instrument selected by the user.
     */
    public void setInstrument(Instrument instrument) {
        this.selectedInstrument = instrument;
    }

    /**
     * Sets the list of composed songs for the user.
     *
     * @param composedSongs The new list of songs composed by the user.
     */
    public void setComposedSongs(List<Song> composedSongs) {
        this.composedSongs = composedSongs;
    }

    /**
     * Gets the unique identifier for the user.
     *
     * @return The unique identifier (UUID) for the user.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the user.
     *
     * @param id The new unique identifier (UUID) for the user.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Gets the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }
}
