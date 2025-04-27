package com.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DataWriter extends DataConstants {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void saveUsers(List<User> users) {
        try (FileWriter writer = new FileWriter(USER_FILE_NAME)) {
            GSON.toJson(users, writer); // directly saves users list
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveSongs(List<Song> songs) {
        try (FileWriter writer = new FileWriter(SONG_FILE_NAME)) {
            GSON.toJson(songs, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
