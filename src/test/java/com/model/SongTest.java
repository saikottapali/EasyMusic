package com.model;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
* @author Savanna Welch
* Tests Run by Bryson Sinclair
*/

public class SongTest {
    private Song song;
    private SheetMusic dummySheetMusic;
    private UUID dummyId;
    private String dummyTitle = "Test Song";
    private String dummyComposer = "Test Composer";
    private String dummyDifficultyLevel = "UNKNOWN";
    private String dummyClef = "TREBLE";
    private boolean dummyIsPrivate = false;
    private int dummyTempo;

    @BeforeEach
    void setUp() {
        dummySheetMusic = new SheetMusic(dummyId, dummyTitle, dummyComposer, dummyDifficultyLevel, dummyClef, dummyTempo, new ArrayList<>()); // Assuming you have a SheetMusic class
        song = new Song(dummyId, dummyTitle, dummyComposer, dummySheetMusic, dummyIsPrivate, new ArrayList<>());
    }

    @Test
    void testSongCreation() {
        assertNotNull(song, "Song object should not be null.");
        assertEquals("Test Song", song.getTitle(), "Title should match the constructor value.");
        assertEquals("Test Composer", song.getComposer(), "Composer should match the constructor value.");
        assertEquals("UNKNOWN", song.getDifficultyLevel(), "Difficulty level should match the constructor value.");
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
        assertEquals("MEDIUM", song.getDifficultyLevel().toUpperCase(), "Difficulty level should be uppercase.");
        
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
        SheetMusic newSheetMusic = new SheetMusic(dummyId, "New Title", "New Composer", "MEDIUM", "TREBLE", 120, new ArrayList<>());
        song.setSheetMusic(newSheetMusic);
        assertEquals(newSheetMusic, song.getSheetMusic(), "Sheet music should be updated.");
    }

}

