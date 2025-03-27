package com.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class DataLoader extends DataConstants {

    // Generalized loadJsonData method that handles both JSONArray and JSONObject
    public static Object loadJsonData(String filePath) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(filePath)) {
            // Parse the JSON file and return either JSONObject or JSONArray
            Object obj = parser.parse(reader);
            return obj;  // Return as a general Object type
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Load users from JSON data
    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        Object jsonData = loadJsonData(USER_FILE_NAME);  // Load JSON data

        if (jsonData instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) jsonData;
            JSONArray userArray = (JSONArray) jsonObject.get("users");

            for (Object obj : userArray) {
                JSONObject userObj = (JSONObject) obj;
                UUID id = UUID.fromString((String) userObj.get("id"));
                String username = (String) userObj.get("username");
                String password = (String) userObj.get("password");
                String email = (String) userObj.get("email");
                String firstName = (String) userObj.get("firstName");
                String lastName = (String) userObj.get("lastName");
                int practiceStreak = ((Long) userObj.get("practiceStreak")).intValue();

                JSONObject instrumentObj = (JSONObject) userObj.get("selectedInstrument");
                Instrument selectedInstrument = parseInstrument(instrumentObj);
                List<Song> composedSongs = new ArrayList<>();
                JSONArray composedSongsArray = (JSONArray) userObj.get("composedSongs");
                if (composedSongsArray != null) {
                    for (Object songIdObj : composedSongsArray) {
                        String songIdString = null;
                        // Check if the songId is a String or JSONObject (or some other structure)
                        if (songIdObj instanceof String) {
                            songIdString = (String) songIdObj;
                        } else if (songIdObj instanceof JSONObject) {
                            songIdString = (String) ((JSONObject) songIdObj).get("id");
                        }

                        if (songIdString != null) {
                            UUID songId = UUID.fromString(songIdString);
                            composedSongs.add(findSongById(songId));
                        }
                    }
                }

                Boolean isLoggedIn = (Boolean) userObj.get("isLoggedIn");

                // Add new User object to the list
                users.add(new User(id, username, password, email, firstName, lastName, 
                    practiceStreak, selectedInstrument, composedSongs, isLoggedIn));
            }
        }
        return users;
    }

    // Helper method to parse Instrument
    private static Instrument parseInstrument(JSONObject instrumentObj) {
        if (instrumentObj == null) return null;  // Handle case where the instrument is missing
    
        // Adjust the keys according to your actual JSON structure
        String name = (String) instrumentObj.get("name");
        String type = (String) instrumentObj.get("type");
    
        // Construct the Instrument object (make sure to pass the right constructor)
        return new Instrument(name, type, null, null, type);  // Adjust constructor as needed
    }

    // Load songs from JSON data
    public static List<Song> loadSongs() {
        List<Song> songs = new ArrayList<>();
        Object jsonData = loadJsonData(SONG_FILE_NAME);  // Load JSON data

        if (jsonData instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) jsonData;
            JSONArray songArray = (JSONArray) jsonObject.get("songs");

            for (Object obj : songArray) {
                JSONObject songObj = (JSONObject) obj;
                UUID id = UUID.fromString((String) songObj.get(SONG_ID));
                String title = (String) songObj.get(SONG_TITLE);
                String composer = (String) songObj.get(SONG_COMPOSER);
                String difficultyLevel = (String) songObj.get(SONG_DIFFICULTY);
                Instrument instrument = (Instrument) songObj.get(SONG_INSTRUMENT);
                Date date = (Date) songObj.get(SONG_DATE);
                boolean isPrivate = (Boolean) songObj.get(SONG_IS_PRIVATE);
                List<String> comments = (List<String>) songObj.get(SONG_COMMENTS);
                List<String> songNotes = (List<String>) songObj.get(SONG_NOTES);
                List<String> tags = (List<String>) songObj.get(SONG_TAGS);

                // Load SheetMusic
                JSONObject sheetMusicObj = (JSONObject) songObj.get(SONG_SHEET_MUSIC);
                SheetMusic sheetMusic = null;
                if (sheetMusicObj != null) {
                    UUID sheetId = UUID.fromString((String) sheetMusicObj.get(SHEET_MUSIC_ID));
                    String sheetTitle = (String) sheetMusicObj.get(SHEET_MUSIC_TITLE);
                    String sheetComposer = (String) sheetMusicObj.get(SHEET_MUSIC_COMPOSER);
                    String notationType = (String) sheetMusicObj.get(SHEET_MUSIC_NOTATION);
                    String sheetDifficulty = (String) sheetMusicObj.get(SHEET_MUSIC_DIFFICULTY);
                    int tempoNum = ((Long) sheetMusicObj.get(SHEET_MUSIC_TEMPO_NUMERATOR)).intValue();
                    int tempoDen = ((Long) sheetMusicObj.get(SHEET_MUSIC_TEMPO_DENOMINATOR)).intValue();
                    String clef = (String) sheetMusicObj.get(SHEET_MUSIC_CLEF);
                    ArrayList<Measure> measures = (ArrayList<Measure>) sheetMusicObj.get(SHEET_MUSIC_MEASURES);

                    sheetMusic = new SheetMusic(sheetId, sheetTitle, sheetComposer, sheetDifficulty, notationType, tempoNum, 
                    tempoDen, clef, measures);
                }

                songs.add(new Song(id, title, composer, difficultyLevel, instrument, date, sheetMusic, isPrivate, 
                comments, songNotes, tags));
            }
        }
        return songs;
    }

    // Helper method to read from file and return as JSONArray
    private static JSONArray readFromFile(String filename) {
        try (FileReader reader = new FileReader(filename)) {
            return (JSONArray) new JSONParser().parse(reader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Helper method to find a song by its ID
    private static Song findSongById(UUID id) {
        for (Song song : loadSongs()) {
            if (song.getId().equals(id)) return song;
        }
        return null;
    }
}
