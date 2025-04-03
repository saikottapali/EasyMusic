package com.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @author Savanna Welch
*/

public class SongTest {
    private Song song;
    private SheetMusic dummySheetMusic;

    @BeforeEach
    void setUp() {
        dummySheetMusic = new SheetMusic(null, null, null, null, 0, 0, null, null); // Assuming you have a SheetMusic class
        song = new Song("1234", "Test Song", "Test Composer", dummySheetMusic, false, new ArrayList<>());
    }

    @Test
    void testSongCreation() {
        assertNotNull(song, "Song object should not be null.");
        assertEquals("Test Song", song.getTitle(), "Title should match the constructor value.");
        assertEquals("Test Composer", song.getComposer(), "Composer should match the constructor value.");
        assertFalse(song.isPrivate(), "Song should be public by default.");
        assertNotNull(song.getDate(), "Date should not be null.");
    }

    @Test
    void testSetTitle() {
        song.setTitle("New Title");
        assertEquals("New Title", song.getTitle(), "Title should update correctly.");
    }

    @Test
    void testSetComposer() {
        song.setComposer("New Composer");
        assertEquals("New Composer", song.getComposer(), "Composer should update correctly.");
    }

    @Test
    void testSetDifficultyLevel() {
        song.setDifficultyLevel("Medium");
        assertEquals("MEDIUM", song.getDifficultyLevel(), "Difficulty level should be uppercase.");
        
        song.setDifficultyLevel(null);
        assertNotEquals(null, song.getDifficultyLevel(), "Difficulty should not accept null values.");
    }

    @Test
    void testPrivacySetting() {
        song.setPrivate(true);
        assertTrue(song.isPrivate(), "Song should be private after setting.");
    }

    @Test
    void testSetAndGetSheetMusic() {
        SheetMusic newSheetMusic = new SheetMusic(null, null, null, null, 0, 0, null, null);
        song.setSheetMusic(newSheetMusic);
        assertEquals(newSheetMusic, song.getSheetMusic(), "Sheet music should be updated.");
    }

}
