package com.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * @author Bryson Sinclair
 */
public class MusicCreationTest {
    private MusicCreation musicCreation;
    private SheetMusic mockSheetMusic;
    private Note mockNote;

    @BeforeEach
    void setUp() {
        musicCreation = new MusicCreation();

        // Creating a mock SheetMusic object
        mockSheetMusic = new SheetMusic(
            UUID.randomUUID().toString(), "Test Title", "Test Composer", "EASY",
            4, 4, "Treble", new ArrayList<>()
        );

        // Creating a mock Note object
        mockNote = new Note("C5", "q");
    }

    @Test
    void testCreateMusic() {
        String title = "Song Title";
        String composer = "Composer Name";
        String difficultyLevel = "MEDIUM";
        Date date = new Date();
        boolean isPrivate = true;
        List<String> songNotes = Arrays.asList("C", "D", "E");

        // Act
        Song song = musicCreation.createMusic(title, composer, difficultyLevel, date, isPrivate, songNotes, mockSheetMusic);

        // Assert
        assertNotNull(song);
        assertEquals(title, song.getTitle());
        assertEquals(composer, song.getComposer());
        assertEquals(difficultyLevel, song.getDifficultyLevel());
        assertEquals(mockSheetMusic, song.getSheetMusic());
        assertEquals(isPrivate, song.isPrivate());
        assertEquals(songNotes, song.getSongNotes());
    }

    @Test
    void testPlaySong_WithSheetMusic() {
        Song song = new Song(UUID.randomUUID().toString(), "Song Title", "Composer", new Date(), 
                             "HARD", mockSheetMusic, false, Arrays.asList("C", "E", "G"));

        // Act & Assert: No exception should be thrown
        assertDoesNotThrow(() -> musicCreation.playSong(song, mockNote));
    }

    @Test
    void testPlaySong_NoSheetMusic() {
        Song song = new Song(UUID.randomUUID().toString(), "Song Title", "Composer", new Date(), 
                             "HARD", null, false, Arrays.asList("C", "E", "G"));

        // Act & Assert: Ensure playSong() does not attempt to play if sheet music is missing
        assertDoesNotThrow(() -> musicCreation.playSong(song, mockNote));
    }
}
