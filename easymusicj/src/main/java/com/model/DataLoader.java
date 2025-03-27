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

public class DataLoader extends DataConstants{
    private static final String USER_FILE = USER_FILE_NAME; //DON'T CHANGE
    private static final String SONG_FILE = SONG_FILE_NAME; //DON"T CHANGE

    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        JSONArray userArray = readFromFile(USER_FILE);
        if (userArray == null) return users;

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

            users.add(new User(id, username, password, email, firstName, lastName, (int) practiceStreak, composedSongs, false));
        }
        return users;
    }

    public static List<Song> loadSongs() {
        List<Song> songs = new ArrayList<>();
        JSONArray songArray = readFromFile(SONG_FILE);
        if (songArray == null) return songs;

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

            SheetMusic sheetMusic = new SheetMusic(musicID, sheetTitle, sheetComposer, sheetDifficultyLevel, "STANDARD", 4, 4, "Treble", new ArrayList<>());

            songs.add(new Song(id, title, composer, sheetMusic, isPrivate, new ArrayList<>()));
        }
        return songs;
    }

    private static JSONArray readFromFile(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            return (JSONArray) new JSONParser().parse(reader);
        } catch (IOException | ParseException e) {
            return null;
        }
    }

    private static List<Song> loadComposedSongs(JSONArray songIDs) {
        List<Song> composedSongs = new ArrayList<>();
        if (songIDs == null) return composedSongs;

        List<Song> allSongs = loadSongs();
        for (Object songID : songIDs) {
            UUID songUUID = UUID.fromString((String) songID);
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
