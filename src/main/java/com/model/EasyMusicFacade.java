package com.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EasyMusicFacade {
    private User currentUser;
    private MusicLibrary songList;
    private UserList userList;
    private Song selectedSong;  // Now a single Song, not a List
    private static EasyMusicFacade instance;

    public EasyMusicFacade() {
        songList = MusicLibrary.getInstance();
        userList = UserList.getInstance();
        this.currentUser = null;
        this.selectedSong = null;
    }

    public static EasyMusicFacade getInstance() {
        if (instance == null) {
            instance = new EasyMusicFacade();
        }
        return instance;
    }

    public User login(String username, String password) {
        for (User user : UserList.getInstance().getAllUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                currentUser.setLoggedIn(true);
                DataWriter.saveUsers(userList.getInstance().getAllUsers());
                return currentUser;
            }
        }
        return null;
    }

    public void logOut() {
        if (currentUser != null) {
            currentUser.setLoggedIn(false);
            DataWriter.saveUsers(UserList.getInstance().getAllUsers());
            currentUser = null;
        }
    }

    public boolean createAccount(String firstName, String lastName, String email, String username, String password) {
        UserList userList = UserList.getInstance();
        if (userList.getUser(username) != null) {
            return false; // Username already exists
        }
        User newUser = new User(UUID.randomUUID(), username, password, email, firstName, lastName, new ArrayList<>(), false);
        userList.addUser(newUser);
        return true;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public Song chooseSong(String songTitle) {
        return songList.getSongs().stream()
                .filter(s -> s.getTitle().equalsIgnoreCase(songTitle))
                .findFirst()
                .orElse(null);
    }

    public boolean playSelectedSong() {
        if (selectedSong != null) {
            selectedSong.playSong();
            return true;
        }
        return false;
    }

    public boolean createSong(String title, String difficultyLevel, boolean isPrivate, List<Note> notes) {
        if (currentUser == null) return false; // Ensure a user is logged in
        try {
            UUID songId = UUID.randomUUID();
            String composerName = getCurrentUser().getUsername();
            Measure measure = new Measure(notes, 120, "4/4"); // assuming 4/4 and 120 bpm for now
            measure.setNotes(notes);
            List<Measure> measures = new ArrayList<>();
            measures.add(measure);
            SheetMusic sheetMusic = new SheetMusic(songId, title, composerName, difficultyLevel, "Treble", 120, measures);
            Song song = new Song(songId, title, composerName, difficultyLevel, sheetMusic, isPrivate, notes);
            songList.getSongs().add(song);
            DataWriter.saveSongs(songList.getSongs());   
            currentUser.getComposedSongs().add(new ComposedSongEntry(songId, title));
            DataWriter.saveUsers(userList.getAllUsers());
            saveSongAsTxt(song);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void saveSongAsTxt(Song song) {
    try {
        File dir = new File("saved_songs");
        if (!dir.exists()) {
            dir.mkdirs(); // Make directory if it doesn't exist
        }

        File songFile = new File(dir, song.getTitle().replaceAll("\\s+", "_") + ".txt");
        FileWriter writer = new FileWriter(songFile);

        writer.write("Title: " + song.getTitle() + "\n");
        writer.write("Composer: " + song.getComposer() + "\n");
        writer.write("Difficulty Level: " + song.getDifficultyLevel() + "\n");
        writer.write("Date: " + song.getDate() + "\n");
        writer.write("Private: " + song.isPrivate() + "\n");
        writer.write("Notes:\n");

        for (Note note : song.getSongNotes()) {
            writer.write(note.getNoteName() + note.getDuration() + " ");
        }

        writer.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


}
    