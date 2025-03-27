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
    private static final String USER_FILE = USER_FILE_NAME;
    private static final String SONG_FILE = SONG_FILE_NAME;

    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        Object jsonData = loadJsonData(USER_FILE_NAME);
            
                if (jsonData instanceof JSONArray) {
                    JSONArray userArray = (JSONArray) jsonData;
                    users = parseUserArray(userArray);
                } else if (jsonData instanceof JSONObject) {
                    JSONObject jsonObject = (JSONObject) jsonData;
                    JSONArray userArray = (JSONArray) jsonObject.get("users");
                    if (userArray != null) {
                        users = parseUserArray(userArray);
                    }
                }
            
                return users;
            }
            
            private static Object loadJsonData(String userFileName) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'loadJsonData'");
            }
        
            private static List<User> parseUserArray(JSONArray userArray) {
        List<User> users = new ArrayList<>();
        for (Object obj : userArray) {
            JSONObject userObj = (JSONObject) obj;
            UUID id = UUID.fromString((String) userObj.get("id"));
            String username = (String) userObj.get("username");
            String password = (String) userObj.getOrDefault("password", userObj.get("hashedPassword"));
            String email = (String) userObj.get("email");
            String firstName = (String) userObj.get("firstName");
            String lastName = (String) userObj.get("lastName");
            int practiceStreak = ((Long) userObj.get("practiceStreak")).intValue();
            boolean isLoggedIn = (Boolean) userObj.get("isLoggedIn");
    
            List<Song> composedSongs = new ArrayList<>();
            // ... (code to parse composedSongs if needed)
    
            users.add(User.fromJson(userObj));
        return users;}
                return users;
    }
    
    // Load songs from JSON data
    public static List<Song> loadSongs() {
        List<Song> songs = new ArrayList<>();
        JSONArray songArray = readFromFile(SONG_FILE);
        

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
            String sheetComposer = (String) sheetMusicJSON.get(USER_USER_NAME);
            String sheetDifficultyLevel = (String) sheetMusicJSON.get(SHEET_MUSIC_DIFFICULTY);

            SheetMusic sheetMusic = new SheetMusic(musicID, sheetTitle, sheetComposer, sheetDifficultyLevel, "STANDARD", 4, 4, "Treble", new ArrayList<>());

            songs.add(new Song(id ,title, composer, sheetMusic ,isPrivate, new ArrayList<>()));
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