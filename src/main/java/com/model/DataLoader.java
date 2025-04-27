package com.model;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class DataLoader extends DataConstants {
    private static final String USER_FILE = USER_FILE_NAME; // DON'T CHANGE
    private static final String SONG_FILE = SONG_FILE_NAME; // DON'T CHANGE
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Make pretty Gson once

    /**
     * Loads the list of users from the JSON file.
     * @return A list of users loaded from the file.
     */
    public static List<User> loadUsers() {
        try (Reader reader = new FileReader(USER_FILE)) {
            List<User> users = gson.fromJson(reader, new TypeToken<List<User>>() {}.getType());
            return users != null ? users : new ArrayList<>(); // In case file is empty
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Loads the list of songs from the JSON file.
     * @return A list of songs loaded from the file.
     */
    public static List<Song> loadSongs() {
        // Load songs from the file and parse them into Song objects
        Gson gson = new Gson();
        List<Song> songs = new ArrayList<>();
        try (FileReader reader = new FileReader(SONG_FILE)) {
            Type listType = new TypeToken<List<Song>>(){}.getType();
            songs = gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return songs;
    }

    /**
     * Loads a list of composed songs for a given user by matching the song IDs.
     * @param songIDs A list of song UUID strings associated with the user's composed songs.
     * @return A list of Song objects corresponding to the composed songs of the user.
     */
    public static List<Song> loadComposedSongs(List<String> songIDs) {
        List<Song> composedSongs = new ArrayList<>();
        if (songIDs == null) return composedSongs;

        List<Song> allSongs = loadSongs();
        for (String songID : songIDs) {
            UUID songUUID = UUID.fromString(songID);
            for (Song song : allSongs) {
                if (song.getId().equals(songUUID)) {
                    composedSongs.add(song);
                    break;
                }
            }
        }
        return composedSongs;
    }
}
