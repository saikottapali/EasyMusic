package com.model;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Cameron Long
 * Tests Run by Bryson Sinclair
 */

public class SheetMusicTest {
    private SheetMusic sheetMusic;
    private Measure measure1, measure2;
    private Song dummySong;
    private UUID dummyId;
    private String dummyTitle = "Test Sheet";
    private String dummyComposer = "Test Composer";
    private String dummyDifficultyLevel = "INTERMEDIATE";
    private String dummyClef = "TREBLE";
    private boolean dummyIsPrivate = false;
    private int dummyTempo;

    @BeforeEach
    void setUp() {
        measure1 = new Measure(new ArrayList<>(), 120, "4/4");
        measure2 = new Measure(new ArrayList<>(), 100, "3/4");

        sheetMusic = new SheetMusic(dummyId, dummyTitle, dummyComposer, dummyDifficultyLevel, dummyClef, dummyTempo, new ArrayList<>());
        dummySong = new Song(dummyId, dummyClef, dummyClef, sheetMusic, dummyIsPrivate, new ArrayList<>());
    }

    @Test
    void testSheetMusicCreation() {
        assertNotNull(sheetMusic, "SheetMusic object should not be null.");
        assertEquals("Test Sheet", sheetMusic.getTitle(), "Title should match.");
        assertEquals("Test Composer", sheetMusic.getComposer(), "Composer should match.");
        assertEquals("Intermediate", sheetMusic.getDifficultyLevel(), "Difficulty level should match.");
        assertEquals("Treble", sheetMusic.getClef(), "Clef should match.");
        assertEquals(4, sheetMusic.getTempo(), "Tempo should match.");
        assertNotNull(sheetMusic.getMeasures(), "Measures list should be initialized.");
        assertTrue(sheetMusic.getMeasures().isEmpty(), "Measures should be empty initially.");
    }

    @Test
    void testAddMeasure() {
        sheetMusic.addMeasure(measure1);
        assertEquals(1, sheetMusic.getMeasures().size(), "Should have one measure after adding.");
        assertEquals(measure1, sheetMusic.getMeasures().get(0), "First measure should match.");
    }

    @Test
    void testRemoveMeasure() {
        sheetMusic.addMeasure(measure1);
        sheetMusic.addMeasure(measure2);
        sheetMusic.removeMeasure(measure1);
        assertEquals(1, sheetMusic.getMeasures().size(), "Should have one measure left.");
        assertEquals(measure2, sheetMusic.getMeasures().get(0), "Remaining measure should match.");
    }

    @Test
    void testSaveToFile() {
        boolean result = sheetMusic.saveToFile(dummySong);
        assertTrue(result, "File saving should return true.");
    }

    @Test
    void testGetJFugueNotation() {
        measure1.toJFugueNotation();  // Assuming it returns a valid string
        measure2.toJFugueNotation();

        sheetMusic.addMeasure(measure1);
        sheetMusic.addMeasure(measure2);

        String notation = sheetMusic.getJFugueNotation();
        assertFalse(notation.isEmpty(), "JFugue notation should not be empty.");
        assertTrue(notation.contains("|"), "Notation should include measure separators.");
    }
}
