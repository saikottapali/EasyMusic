package com.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Bryson Sinclair
 * Tests Run by Bryson Sinclair
 */
public class MusicCreationTest {
    private MusicCreation musicCreation;
    private SheetMusic mockSheetMusic;
    private Note mockNote;
    private UUID dummyId;
    private String dummyTitle = "Test Song";
    private String dummyComposer = "Test Composer";
    private String dummyDifficultyLevel = "UNKNOWN";
    private String dummyClef = "TREBLE";
    private Date dummyDate;
    private boolean dummyIsPrivate = false;
    private int dummyTempo;
    private List<Note> dummySongNotes = Arrays.asList(new Note("C4", "q"), new Note("E4", "e"), new Note("G4", "h"));


    @BeforeEach
    void setUp() {
        musicCreation = new MusicCreation();

        // Creating a mock SheetMusic object
        mockSheetMusic = new SheetMusic(dummyId, dummyTitle, dummyComposer, dummyDifficultyLevel, dummyClef, dummyTempo, new ArrayList<>());

        // Creating a mock Note object
        mockNote = new Note("C5", "q");
    }

    @Test
    void testCreateMusic() {
        // Act
        Song song = musicCreation.createMusic(dummyTitle, dummyComposer, dummyDifficultyLevel, dummyDate, dummyIsPrivate, mockSheetMusic, dummySongNotes);

        // Assert
        assertNotNull(song);
        assertEquals(dummyTitle, song.getTitle());
        assertEquals(dummyComposer, song.getComposer());
        assertEquals(dummyDifficultyLevel, song.getDifficultyLevel());
        assertEquals(mockSheetMusic, song.getSheetMusic());
        assertEquals(dummyIsPrivate, song.isPrivate());
        assertEquals(dummySongNotes, song.getSongNotes());
    }

    @Test
    void testPlaySong_WithSheetMusic() {
        Song song = new Song(dummyId, dummyTitle, dummyComposer, mockSheetMusic, dummyIsPrivate, dummySongNotes);

        // Act & Assert: No exception should be thrown
        assertDoesNotThrow(() -> musicCreation.playSong(song, mockNote));
    }

    @Test
    void testPlaySong_NoSheetMusic() {
        Song song = new Song(dummyId, dummyTitle, dummyComposer, null, dummyIsPrivate, dummySongNotes);
        // Act & Assert: Ensure playSong() does not attempt to play if sheet music is missing
        assertDoesNotThrow(() -> musicCreation.playSong(song, mockNote));
    }
}

