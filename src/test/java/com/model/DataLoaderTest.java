package com.model;

import java.util.List;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import org.powermock.api.mockito.PowerMockito;

/**
* @author Savanna Welch
* Tests run by Bryson Sinclair
*/

public class DataLoaderTest {

    private JSONArray mockUserData;
    private JSONArray mockSongData;

    @BeforeEach
    void setUp() {
        mockUserData = new JSONArray();
        mockSongData = new JSONArray();

        // Mock User JSON Data
        JSONObject userJSON = new JSONObject();
        userJSON.put(DataConstants.USER_ID, UUID.randomUUID().toString());
        userJSON.put(DataConstants.USER_USER_NAME, "testUser");
        userJSON.put(DataConstants.USER_PASSWORD, "password123");
        userJSON.put(DataConstants.USER_EMAIL, "test@example.com");
        userJSON.put(DataConstants.USER_FIRST_NAME, "John");
        userJSON.put(DataConstants.USER_LAST_NAME, "Doe");
        userJSON.put(DataConstants.USER_PRACTICE_STREAK, 5);
        userJSON.put(DataConstants.USER_COMPOSED_SONGS, new JSONArray());

        mockUserData.add(userJSON);

        // Mock Song JSON Data
        JSONObject songJSON = new JSONObject();
        songJSON.put(DataConstants.SONG_ID, UUID.randomUUID().toString());
        songJSON.put(DataConstants.SONG_TITLE, "Test Song");
        songJSON.put(DataConstants.SONG_COMPOSER, "John Doe");
        songJSON.put(DataConstants.SONG_DIFFICULTY, "Easy");
        songJSON.put(DataConstants.SONG_IS_PRIVATE, false);
        
        JSONObject sheetMusicJSON = new JSONObject();
        sheetMusicJSON.put(DataConstants.SHEET_MUSIC_ID, UUID.randomUUID().toString());
        sheetMusicJSON.put(DataConstants.SHEET_MUSIC_TITLE, "Test Sheet");
        sheetMusicJSON.put(DataConstants.SHEET_MUSIC_COMPOSER, "John Doe");
        sheetMusicJSON.put(DataConstants.SHEET_MUSIC_DIFFICULTY, "Easy");
        sheetMusicJSON.put(DataConstants.SHEET_MUSIC_CLEF, "Treble");
        sheetMusicJSON.put(DataConstants.SHEET_MUSIC_TEMPO, 4);
        sheetMusicJSON.put(DataConstants.SHEET_MUSIC_MEASURES, new JSONArray());

        songJSON.put(DataConstants.SONG_SHEET_MUSIC, sheetMusicJSON);
        mockSongData.add(songJSON);
    }

    @Test
    void testLoadUsers() throws Exception {
        // Prepare PowerMock to mock the static method
        PowerMockito.spy(DataLoader.class); // Spy the DataLoader class
        PowerMockito.doReturn(mockUserData).when(DataLoader.class, "readFromFile", anyString()); // Mock private method

        // Call the method
        List<User> users = DataLoader.loadUsers();

        // Perform assertions
        assertFalse(users.isEmpty(), "User list should not be empty");
        assertEquals("testUser", users.get(0).getUsername(), "Username should match");
        assertEquals("test@example.com", users.get(0).getEmail(), "Email should match");
        assertEquals(5, users.get(0).getPracticeStreak(), "Practice streak should match");
    }

    @Test
    void testLoadSongs() throws Exception {
        // Prepare PowerMock to mock the static method
        PowerMockito.spy(DataLoader.class); // Spy the DataLoader class
        PowerMockito.doReturn(mockSongData).when(DataLoader.class, "readFromFile", anyString()); // Mock private method

        // Call the method
        List<Song> songs = DataLoader.loadSongs();

        // Perform assertions
        assertFalse(songs.isEmpty(), "Song list should not be empty");
        assertEquals("Test Song", songs.get(0).getTitle(), "Title should match");
        assertEquals("John Doe", songs.get(0).getComposer(), "Composer should match");
        assertEquals("Easy", songs.get(0).getDifficultyLevel(), "Difficulty level should match");
        assertFalse(songs.get(0).isPrivate(), "Song should not be private");
    }
}

