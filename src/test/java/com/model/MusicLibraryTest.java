package com.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
* @author Savanna Welch
* Tests Run by Bryson Sinclair
*/

public class MusicLibraryTest {

    private MusicLibrary musicLibrary;
    private DataLoader mockDataLoader;
    private DataWriter mockDataWriter;
    private UUID dummyId;
    private UUID dummyId2;
    private String dummyTitle = "Song1";
    private String dummyTitle2 = "Song2";
    private String dummyComposer = "Composer1";
    private String dummyComposer2 = "Composer2";
    private boolean dummyIsPrivate = false;
    private SheetMusic dummySheetMusic;
    
    @BeforeEach
    void setUp() {
        // Mock DataLoader and DataWriter to isolate the tests from actual file operations
        mockDataLoader = mock(DataLoader.class);
        mockDataWriter = mock(DataWriter.class);
        
        // For the purpose of testing, we mock the loadSongs to return a predefined list
        List<Song> predefinedSongs = Arrays.asList(
            new Song(dummyId, dummyTitle, dummyComposer, dummySheetMusic, dummyIsPrivate, new ArrayList<>()),
            new Song(dummyId2, dummyTitle2, dummyComposer2, dummySheetMusic, dummyIsPrivate, new ArrayList<>())
        );
        when(mockDataLoader.loadSongs()).thenReturn(predefinedSongs);
        
        
        musicLibrary = new MusicLibrary();
    }

    @Test
    void testGetInstance() {
        // Test if the singleton pattern works correctly
        MusicLibrary library1 = MusicLibrary.getInstance();
        MusicLibrary library2 = MusicLibrary.getInstance();
        
        assertSame(library1, library2, "Both instances should be the same.");
    }

    @Test
    void testAddSong() {
        // Add a song to the library
        Song newSong = new Song(null, null, null, null, false, null);
        musicLibrary.addSong(newSong);
        
        // Verify that the song was added
        List<Song> songs = musicLibrary.getSongs();
        assertTrue(songs.contains(newSong), "The song should be added to the library.");
        
        // Verify that DataWriter.saveSongs was called
        verify(mockDataWriter, times(1)).saveSongs(songs);
    }

    @Test
    void testGetSongs() {
        // Test the list of songs in the library
        List<Song> songs = musicLibrary.getSongs();
        
        // Verify that the library is initialized with the predefined songs
        assertNotNull(songs, "The song list should not be null.");
        assertEquals(2, songs.size(), "The song list should contain 2 songs.");
    }
}


