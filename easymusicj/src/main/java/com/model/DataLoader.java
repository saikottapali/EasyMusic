package com.model;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataLoader extends DataConstants {

    private static final String SONGS_FILE = "songs.json";

    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();

        try {
            FileReader reader = new FileReader(USER_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray peopleJSON = (JSONArray) parser.parse(reader);

            for (int i = 0; i < peopleJSON.size(); i++) {
                JSONObject personJSON = (JSONObject) peopleJSON.get(i);

                UUID id = UUID.fromString((String) personJSON.get(USER_ID));
                String userName = (String) personJSON.get(USER_USER_NAME);
                String fullName = (String) personJSON.get(USER_Full_NAME);
                int age = ((Long) personJSON.get(USER_AGE)).intValue();
                String phoneNumber = (String) personJSON.get(USER_PHONE_NUMBER);

                users.add(new User(id, userName, fullName, age, phoneNumber));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

     public static JSONArray loadSongs() {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(SONGS_FILE)) {
            Object obj = parser.parse(reader);
            return (JSONArray) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return new JSONArray(); 
        }
    }
}
