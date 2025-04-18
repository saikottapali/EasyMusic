package com.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class EasyMusicFacade {
    private List<User> user;                  // The logged-in user
    private MusicCreation musicPost;
    private MusicLibrary songList;    // The music creation platform (handles song creation, saving, etc.)
    private List<Song> song;          // The selected song for playback, upload, or download
    /**
     * Constructor for EasyMusicFacade.
     * Initializes the facade with the logged-in user and the music creation platform.
     *
     * 
     */
    public EasyMusicFacade() {
        user = DataLoader.loadUsers();
        song = DataLoader.loadSongs();
        musicPost = new MusicCreation();
        songList = MusicLibrary.getInstance();
    }

    public boolean createAccount(String firstName, String lastName, String username, String password, String email) {
        UserList userList = UserList.getInstance();
        if (userList.getUser(username) != null) {
            return false; // Username already exists
        }
        User newUser = new User(UUID.randomUUID(), username, password, email, firstName, lastName, 0, new ArrayList<>(), false);
        userList.addUser(newUser);
        return true;
    }

    /**
     * Logs in the user using the provided username and password.
     *
     * @param username The username.
     * @param password The password.
     */
    public User login(String username, String password) {
        for (User user : user) {
            if (user.getUsername() != null && user.getPassword() != null &&
                user.getUsername().equals(username) && user.getPassword().equals(password)) {
                user.setLoggedIn(true);
                return user;
            }
        }
        return null;
    }

    /**
     * Logs out the user.
     */
    public void logOut() {
        this.user = null;
    }

    public String viewProfile() {
        return user != null ? user.toString() : null;
    }

    /**
     * Allows the user to select a song by its title.
     *
     * @param selectedSong The title of the song to select.
     * @return The selected song, or null if the song is not found.
     */
    public boolean chooseSong(List<Song> selectedSong) {
        selectedSong = songList.getSongs();
        return selectedSong != null;  
    }

    /**
     * Views the sheet music of a selected song.
     *
     * @param selectedSong The title of the song to view sheet music for.
     * @return The sheet music for the song, or null if the song is not found.
     */
    public String viewSheetMusic(List<Song> selectedSong) {
        Song song = songList.getSongs().stream()
                .filter(s -> s.getTitle().equals(selectedSong))
                .findFirst()
                .orElse(null);
        if (song != null) {
            return song.getSheetMusic().toString();
        }
        return null;
    }

    /**
     * Plays a given note.
     *
     * @param note The note to play.
     */
    public void playNote(Note note) {
        note.play();  // Assuming Note has a play method to play the note's sound
    }

    /**
     * Saves the selected song to the music platform.
     *
     * @param song The song to save.
     * @return True if the song was saved successfully.
     */
    public boolean saveMusic(SheetMusic sheetMusic) {
        if (song != null) {
            return sheetMusic.saveToFile(song);  // Prints sheet music to a text file
      }  
        return false;  // Return false if no song is selected
    }
    /**
     * Creates a new song with the given details and returns the created song.
     *
     * @param id The ID of the song.
     * @param title The title of the song.
     * @param composer The composer of the song.
     * @param difficultyLevel The difficulty level of the song.
     * @param date The date of creation.
     * @param sheetMusic The sheet music for the song.
     * @param isPrivate Whether the song is private or public.
     * @return The created song.
     */
    public boolean createMusic(String title, String difficultyLevel, boolean isPrivate, 
    SheetMusic sheetMusic, List<Note> songNotes) {
        if(user != null && ((User) user).isLoggedIn()) {
            String composer = user.getFirst() + " " + user.getLast();
            Song newSong = musicPost.createMusic(title, composer, difficultyLevel, 
            new Date(), isPrivate, sheetMusic, songNotes);
            return newSong != null;
        }
        return false;
    }
    /**
     * Plays the currently selected song.
     */
    public boolean playSelectedSong() {
        if (song != null) {
            ((Song) song).playSong();
            return true;  
        }
        return false;
    }

    public String[] getAvailableSongs() {
        List<Song> songs = MusicLibrary.getInstance().getSongs();
        return songs.stream().map(Song::getTitle).toArray(String[]::new);
    }

    public boolean playSong(String songTitle) {
        Song song = MusicLibrary.getInstance().getSongs().stream()
                .filter(s -> s.getTitle().equalsIgnoreCase(songTitle))
                .findFirst()
                .orElse(null);
        if (song == null || song.getSheetMusic() == null) {
            return false;
        }
        song.playSong();
        return true;
    }

    public boolean createSong(String title, String difficultyLevel, boolean isPrivate, String notesInput) {
        if (user == null) return false; // Ensure a user is logged in

        List<String> noteNames = Arrays.asList(notesInput.split(",\\s*"));
        ArrayList<Note> notes = new ArrayList<>();
        for (String noteName : noteNames) {
            notes.add(new Note(noteName, "q"));
        }

        Measure measure = new Measure(notes, 120, "4/4");
        ArrayList<Measure> measures = new ArrayList<>(List.of(measure));
        UUID id = UUID.randomUUID();
        SheetMusic sheetMusic = new SheetMusic(id, notesInput, notesInput, notesInput, notesInput, 0, measures);
        Song newSong = new Song(id, title, ((User) user).getUsername(), sheetMusic,isPrivate, new ArrayList<>());

        ((User) user).addComposedSong(newSong);
        MusicLibrary.getInstance().addSong(newSong);
        return true;
    }
}

