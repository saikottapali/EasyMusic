package com.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {

    /**
     * Saves the list of users to a JSON file.
     * This method converts a list of User objects into a JSON array and writes it to a file.
     *
     * @param users The list of User objects to save.
     */
    public static void saveUsers(List<User> users) {
        JSONArray jsonArray = new JSONArray(); // Initialize an empty JSON array to hold user objects
    
        for (User user : users) {
            JSONObject userObject = new JSONObject(); // Create a JSON object for each user
            userObject.put(USER_ID, user.getId().toString());
            userObject.put(USER_USER_NAME, user.getUsername());
            userObject.put(USER_PASSWORD, user.getPassword());
            userObject.put(USER_EMAIL, user.getEmail());
            userObject.put(USER_FIRST_NAME, user.getFirstName());
            userObject.put(USER_LAST_NAME, user.getLastName());
            userObject.put(USER_PRACTICE_STREAK, user.getPracticeStreak());
            userObject.put(USER_LOGGED_IN, user.isLoggedIn());
    
            // Serialize composed songs as a JSON array
            JSONArray composedSongsArray = new JSONArray();
            for (Song song : user.getComposedSongs()) {
                JSONObject songObject = new JSONObject();
                songObject.put(SONG_ID, song.getId().toString());
                songObject.put(SONG_TITLE, song.getTitle());
                songObject.put(SONG_SHEET_MUSIC, new JSONObject()); 
                composedSongsArray.add(songObject); // Add the song object to the array
            }
    
            userObject.put(USER_COMPOSED_SONGS, composedSongsArray);  // Add the composed songs array to the user object
            jsonArray.add(userObject); // Add the user JSON object to the array
        }
    
        // Write jsonArray to File
        writeToFile(USER_FILE_NAME, jsonArray);
    }

    /**
     * Saves the list of songs to a JSON file.
     * Converts a list of Song objects into a JSON array and writes it to a file.
     *
     * @param songs The list of Song objects to save.
     */
    public static void saveSongs(List<Song> songs) {
        JSONArray songArray = new JSONArray(); // Initialize an empty JSON array to hold song objects
    
        for (Song song : songs) {
            JSONObject songObj = new JSONObject(); // Create a JSON object for each song
            songObj.put(SONG_ID, song.getId().toString());
            songObj.put(SONG_TITLE, song.getTitle());
            songObj.put(SONG_COMPOSER, song.getComposer());
            songObj.put(SONG_DIFFICULTY, song.getDifficultyLevel().toString());
            songObj.put(SONG_DATE, song.getDate().toString()); // Ensure date is in string format
            songObj.put(SONG_IS_PRIVATE, song.isPrivate());
    
            // Save the SheetMusic object for the song if it exists
            JSONObject sheetMusicObj = new JSONObject();
            if (song.getSheetMusic() != null) {
                SheetMusic sheet = song.getSheetMusic();
                sheetMusicObj.put(SHEET_MUSIC_ID, sheet.getMusicID().toString());
                sheetMusicObj.put(SHEET_MUSIC_TITLE, sheet.getTitle());
                sheetMusicObj.put(SHEET_MUSIC_COMPOSER, sheet.getComposer());
                sheetMusicObj.put(SHEET_MUSIC_DIFFICULTY, sheet.getDifficultyLevel().toString());
                sheetMusicObj.put(SHEET_MUSIC_TEMPO, sheet.getTempo());
                sheetMusicObj.put(SHEET_MUSIC_CLEF, sheet.getClef());
    
                // Save measures of the sheet music
                JSONArray measuresArray = new JSONArray();
                for (Measure measure : sheet.getMeasures()) {
                    JSONObject measureObj = new JSONObject();
                    measureObj.put(MEASURE_TEMPO, measure.getTempo());
                    measureObj.put(MEASURE_TIME_SIGNATURE, measure.getTimeSignature());
    
                    // Save the notes of the measure
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
                sheetMusicObj.put(SHEET_MUSIC_MEASURES, measuresArray); // Add the measures to the sheet music
            }
    
            songObj.put(SONG_SHEET_MUSIC, sheetMusicObj); // Add the sheet music object to the song JSON object
            songArray.add(songObj); // Add the song JSON object to the song array
        }
        
        // Write the song array to the file
        writeToFile(SONG_FILE_NAME, songArray); // Call the helper method to write the data to a file
    }

    /**
     * Helper method to write a JSONArray to a file.
     * This method uses FileWriter to write the JSON data into a file.
     *
     * @param filename The name of the file to write to.
     * @param jsonArray The JSON array to write to the file.
     */
    private static void writeToFile(String filename, JSONArray jsonArray) {
        try (FileWriter file = new FileWriter(filename)) {
            file.write(jsonArray.toJSONString()); // Convert the JSON array to string and write to file
            file.flush(); // Ensure that the data is written to the file
        } catch (IOException e) {
            e.printStackTrace(); // Print error if file writing fails
        }
    }
}

