package com.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {
    public static void saveUsers() {
        User users = User.getInstance();
        ArrayList<User> userList = users.getUsers();
        JSONArray jsonUsers = new JSONArray();

        for (int i = 0; i < userList.size(); i++) {
            jsonUsers.add(getUserJSON(userList.get(i)));
        }

        try (FileWriter file = new FileWriter(USER_FILE_NAME)) {
            file.write(jsonUsers.toJSONString());
            file.flush();
        } catch (IOException e) { 
            e.printStackTrace();
        }
    }

    private static final String SONGS_FILE = "songs.json";

    public static void saveSong(Song song) {
        JSONArray songsList = DataLoader.loadSongs(); // Load existing songs

        JSONObject songObject = new JSONObject();
        songObject.put("id", song.getId().toString());
        songObject.put("name", song.getName());
        songObject.put("title", song.getTitle());
        songObject.put("composer", song.getComposer());
        songObject.put("difficultyLevel", song.getDifficultyLevel());
        songObject.put("instrument", song.getInstrument().toString());
        songObject.put("notes", song.getSongNotes());
        songObject.put("isPrivate", song.isLoggedIn());

        songsList.add(songObject); // Add new song to the list

        try (FileWriter file = new FileWriter(SONGS_FILE)) {
            file.write(songsList.toJSONString()); // Write updated list to file
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_ID, user.getId().toString()); 
        userDetails.put(USER_USER_NAME, user.getUsername());
        userDetails.put(USER_FULL_NAME, user.getFullName());
        userDetails.put(USER_AGE, user.getAge());
        userDetails.put(USER_PHONE_NUMBER, user.getPhoneNumber());

        return userDetails;
    }
}
