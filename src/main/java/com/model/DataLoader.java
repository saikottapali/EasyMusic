package com.model;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataLoader extends DataConstants {
    private static final String USER_FILE = USER_FILE_NAME; //DON'T CHANGE
    private static final String SONG_FILE = SONG_FILE_NAME; //DON'T CHANGE

    /**
     * Loads the list of users from the JSON file.
     * Reads the user data, parses it into User objects, and returns them as a list.
     *
     * @return A list of users loaded from the file.
     */
    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        JSONArray userArray = readFromFile(USER_FILE); // Read the users data from the file
        if (userArray == null) return users; // Return an empty list if file reading fails

        // Parse each user in the array and convert it into a User object
        for (Object obj : userArray) {
            JSONObject userJSON = (JSONObject) obj;
            UUID id = UUID.fromString((String) userJSON.get(USER_ID));
            String username = (String) userJSON.get(USER_USER_NAME);
            String password = (String) userJSON.get(USER_PASSWORD);
            String email = (String) userJSON.get(USER_EMAIL);
            String firstName = (String) userJSON.get(USER_FIRST_NAME);
            String lastName = (String) userJSON.get(USER_LAST_NAME);
            long practiceStreak = (long) userJSON.get(USER_PRACTICE_STREAK);
            List<Song> composedSongs = loadComposedSongs((JSONArray) userJSON.get(USER_COMPOSED_SONGS));

            // Add the user object to the users list
            users.add(new User(id, username, password, email, firstName, lastName, (int) practiceStreak, composedSongs, false));
        }
        return users; // Return the list of users
    }

    /**
     * Loads the list of songs from the JSON file.
     * Reads the song data, parses it into Song objects, and returns them as a list.
     *
     * @return A list of songs loaded from the file.
     */
    public static List<Song> loadSongs() {
        List<Song> songs = new ArrayList<>();
        JSONArray songArray = readFromFile(SONG_FILE); // Read the songs data from the file
        if (songArray == null) return songs; // Return an empty list if file reading fails

        // Parse each song in the array and convert it into a Song object
        for (Object obj : songArray) {
            JSONObject songJSON = (JSONObject) obj;
            UUID id = UUID.fromString((String) songJSON.get(SONG_ID));
            String title = (String) songJSON.get(SONG_TITLE);
            String composer = (String) songJSON.get(SONG_COMPOSER);
            String difficultyLevel = (String) songJSON.get(SONG_DIFFICULTY);
            boolean isPrivate = (boolean) songJSON.get(SONG_IS_PRIVATE);

            JSONObject sheetMusicJSON = (JSONObject) songJSON.get(SONG_SHEET_MUSIC);
            UUID musicID = UUID.fromString((String) sheetMusicJSON.get(SHEET_MUSIC_ID));
            String sheetTitle = (String) sheetMusicJSON.get(SHEET_MUSIC_TITLE);
            String sheetComposer = (String) sheetMusicJSON.get(SHEET_MUSIC_COMPOSER);
            String sheetDifficultyLevel = (String) sheetMusicJSON.get(SHEET_MUSIC_DIFFICULTY);

            // Create the SheetMusic object for this song
            SheetMusic sheetMusic = new SheetMusic(musicID, sheetTitle, sheetComposer, sheetDifficultyLevel, "STANDARD", 4, 4, "Treble", new ArrayList<>());

            // Add the song object to the songs list
            songs.add(new Song(id, title, composer, sheetMusic, isPrivate, new ArrayList<>()));
        }
        return songs; // Return the list of songs
    }

    /**
     * Helper method to read data from a JSON file.
     * Uses JSONParser to read and parse the JSON data from the specified file.
     *
     * @param filePath The path of the file to read from.
     * @return A JSONArray representing the content of the file, or null if an error occurs.
     */
    private static JSONArray readFromFile(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            // Parse and return the JSON array from the file
            return (JSONArray) new JSONParser().parse(reader);
        } catch (IOException | ParseException e) {
            // Return null in case of an exception (e.g., file not found or JSON parse error)
            return null;
        }
    }

    /**
     * Loads a list of composed songs for a given user by matching the song IDs.
     * This method fetches all songs and compares their IDs to those associated with the user.
     *
     * @param songIDs A JSONArray of song IDs associated with the user's composed songs.
     * @return A list of Song objects corresponding to the composed songs of the user.
     */
    private static List<Song> loadComposedSongs(JSONArray songIDs) {
        List<Song> composedSongs = new ArrayList<>();
        if (songIDs == null) return composedSongs; // Return empty list if no songs are composed

        // Load all available songs
        List<Song> allSongs = loadSongs();
        for (Object songID : songIDs) {
            UUID songUUID = UUID.fromString((String) songID);
            for (Song song : allSongs) {
                if (song.getId().equals(songUUID)) {
                    composedSongs.add(song); // Add the song if it matches the user's composed song ID
                    break;
                }
            }
        }
        return composedSongs; // Return the list of composed songs
    }
}
