package com.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
* @author Cameron Long
*/

class SheetMusicTest {
    private SheetMusic sheetMusic;
    private Measure measure1, measure2;
    private Song dummySong;

    @BeforeEach
    void setUp() {
        measure1 = new Measure(new ArrayList<>(), 120, "4/4");
        measure2 = new Measure(new ArrayList<>(), 100, "3/4");

        sheetMusic = new SheetMusic();
        dummySong = new Song("1234", "Test Song", "Test Composer", sheetMusic, false, new ArrayList<>());
    }

    @Test
    void testSheetMusicCreation() {
        assertNotNull(sheetMusic, "SheetMusic object should not be null.");
        assertEquals("Test Sheet", sheetMusic.getTitle());
        assertEquals("Test Composer", sheetMusic.getComposer());
        assertEquals("Intermediate", sheetMusic.getDifficultyLevel());
        assertEquals("Treble", sheetMusic.getClef());
        assertEquals(4, sheetMusic.getTempoNumerator());
        assertEquals(4, sheetMusic.getTempoDenominator());
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
