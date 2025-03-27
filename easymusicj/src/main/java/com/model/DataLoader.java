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

    private static final Object USER_USERNAME = null;
    
        public static List<User> loadUsers() {
            List<User> users = new ArrayList<>();
            JSONArray userArray = readFromFile(USER_FILE_NAME);
            if (userArray == null) return users;
    
            for (Object obj : userArray) {
                JSONObject userObj = (JSONObject) obj;
                UUID id = UUID.fromString((String) userObj.get(USER_ID));
                String username = (String) userObj.get(USER_USERNAME);
                String password = (String) userObj.get(USER_PASSWORD);
                String email = (String) userObj.get(USER_EMAIL);
                String firstName = (String) userObj.get(USER_FIRST_NAME);
                String lastName = (String) userObj.get(USER_LAST_NAME);
                int practiceStreak = ((Long) userObj.get(USER_PRACTICE_STREAK)).intValue();
                Instrument selectedInstrument = (Instrument) userObj.get(USER_SELECTED_INSTRUMENT);
                List<Song> userComposedSongs = (List<Song>) userObj.get(USER_COMPOSED_SONGS);
                Boolean isLoggedIn = (Boolean) userObj.get(USER_LOGGED_IN);

                List<Song> composedSongs = new ArrayList<>();
                JSONArray composedSongsArray = (JSONArray) userObj.get(USER_COMPOSED_SONGS);
                if (composedSongsArray != null) {
                    for (Object songId : composedSongsArray) {
                        composedSongs.add(findSongById(UUID.fromString((String) songId)));
                    }
                }

                users.add(new User(id, username, password, email, firstName, lastName, 
                practiceStreak, selectedInstrument, userComposedSongs));
        }
        return users;
    }

    public static List<Song> loadSongs() {
        List<Song> songs = new ArrayList<>();
        JSONArray songArray = readFromFile(SONG_FILE_NAME);
        if (songArray == null) return songs;

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
        return songs;
    }

    private static JSONArray readFromFile(String filename) {
        try (FileReader reader = new FileReader(filename)) {
            return (JSONArray) new JSONParser().parse(reader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Song findSongById(UUID id) {
        for (Song song : loadSongs()) {
            if (song.getId().equals(id)) return song;
        }
        return null;
    }
}
