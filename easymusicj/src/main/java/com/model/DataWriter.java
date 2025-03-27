package com.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {

    public static void saveUsers(List<User> users) {
        JSONArray jsonArray = new JSONArray();
        for (User user : users) {
            JSONObject userObject = new JSONObject();
            userObject.put("id", user.getId().toString());
            userObject.put("username", user.getUsername());
            userObject.put("hashedPassword", user.getPassword());  // Use "hashedPassword" instead of "password"
            userObject.put("email", user.getEmail());
            userObject.put("firstName", user.getFirstName());
            userObject.put("lastName", user.getLastName());
            userObject.put("practiceStreak", user.getPracticeStreak());
            userObject.put("isLoggedIn", user.isLoggedIn());
            userObject.put("hashedPassword", user.getHashedPassword());
            userObject.put("composedSongs", new JSONArray());  // Assuming empty composedSongs for simplicity
            userObject.put(USER_LOGGED_IN, user.isLoggedIn());

            jsonArray.add(userObject);
        }
        writeToFile(USER_FILE_NAME, jsonArray);
    }

    public static void saveSongs(List<Song> songs) {
        JSONArray songArray = new JSONArray();
        for (Song song : songs) {
            JSONObject songObj = new JSONObject();
            songObj.put(SONG_ID, song.getId().toString());
            songObj.put(SONG_TITLE, song.getTitle());
            songObj.put(SONG_COMPOSER, song.getComposer());

            // Ensure difficulty level is never null
            String difficultyLevel = Objects.requireNonNullElse(song.getDifficultyLevel(), "Unknown");
            songObj.put(SONG_DIFFICULTY, difficultyLevel);

            songObj.put(SONG_DATE, song.getDate());
            songObj.put(SONG_IS_PRIVATE, song.isPrivate());
            songObj.put(SONG_NOTES, song.getSongNotes());
            songObj.put(SONG_TAGS, song.getTags());

            // Save SheetMusic object
            JSONObject sheetMusicObj = new JSONObject();
            if (song.getSheetMusic() != null) {
                SheetMusic sheet = song.getSheetMusic();
                sheetMusicObj.put(SHEET_MUSIC_ID, sheet.getMusicID());
                sheetMusicObj.put(SHEET_MUSIC_TITLE, sheet.getTitle());
                sheetMusicObj.put(SHEET_MUSIC_COMPOSER, sheet.getComposer());

                // Ensure sheet music difficulty level is never null
                String sheetDifficulty = Objects.requireNonNullElse(sheet.getDifficultyLevel(), "Unknown");
                sheetMusicObj.put(SHEET_MUSIC_DIFFICULTY, sheetDifficulty);

                sheetMusicObj.put(SHEET_MUSIC_NOTATION, sheet.getNotationType());
                sheetMusicObj.put(SHEET_MUSIC_TEMPO_NUMERATOR, sheet.getTempoNumerator());
                sheetMusicObj.put(SHEET_MUSIC_TEMPO_DENOMINATOR, sheet.getTempoDenominator());
                sheetMusicObj.put(SHEET_MUSIC_CLEF, sheet.getClef());

                // Save measures
                JSONArray measuresArray = new JSONArray();
                for (Measure measure : sheet.getMeasures()) {
                    JSONObject measureObj = new JSONObject();
                    measureObj.put(MEASURE_TEMPO, measure.getTempo());
                    measureObj.put(MEASURE_TIME_SIGNATURE, measure.getTimeSignature());

                    // Save notes
                    JSONArray notesArray = new JSONArray();
                    for (Note note : measure.getNotes()) {
                        JSONObject noteObj = new JSONObject();
                        noteObj.put(NOTE_PITCH, note.getPitch());
                        noteObj.put(NOTE_DURATION, note.getDuration());
                        notesArray.add(noteObj);
                    }
                    measureObj.put(MEASURE_NOTES, notesArray);
                    measuresArray.add(measureObj);
                }
                sheetMusicObj.put(SHEET_MUSIC_MEASURES, measuresArray);
            }

            songObj.put(SONG_SHEET_MUSIC, sheetMusicObj);
            songArray.add(songObj);
        }
        writeToFile(SONG_FILE_NAME, songArray);
    }

    private static void writeToFile(String filename, JSONArray jsonArray) {
        try (FileWriter file = new FileWriter(filename)) {
            file.write(jsonArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
