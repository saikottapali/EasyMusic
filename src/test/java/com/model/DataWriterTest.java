package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyList;
import org.mockito.MockedStatic;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
* @author Savanna Welch
* Tests Run by Bryson Sinclair
*/

public class DataWriterTest {

    private User testUser;
    private Song testSong;
    private SheetMusic testSheetMusic;
    private UUID dummyId;
    private String dummyTitle = "Test Song";
    private String dummyComposer = "Test Composer";
    private String dummyDifficultyLevel = "Medium";
    private String dummyClef = "TREBLE";
    private boolean dummyIsPrivate = false;
    private int dummyTempo;

    @BeforeEach
    void setUp() {
        
        User testUser = mock(User.class);

        
        when(testUser.getUsername()).thenReturn("testUser");
        when(testUser.getPassword()).thenReturn("testPassword");
        
        List<Measure> testMeasures = new ArrayList<>();
        testMeasures.add(new Measure(new ArrayList<>(), 120, "4/4"));

        testSheetMusic = new SheetMusic(dummyId, dummyTitle, dummyComposer, dummyDifficultyLevel, dummyClef, dummyTempo, testMeasures);  
        List<Note> testNotes = new ArrayList<>();
        testNotes.add(new Note("C4", "q"));
       
        testSong = new Song(dummyId, dummyTitle, dummyComposer, testSheetMusic, dummyIsPrivate, testNotes);
    }

    @Test
    void testSaveUsers() {
        List<User> mockUsers = List.of(testUser);

        try (MockedStatic<DataWriter> mockedStatic = mockStatic(DataWriter.class)) {
            
            mockedStatic.when(() -> DataWriter.saveUsers(mockUsers)).thenCallRealMethod();

          
            DataWriter.saveUsers(mockUsers);

            
            mockedStatic.verify(() -> DataWriter.saveUsers(mockUsers), times(1));
        }
    }

    @Test
    void testSaveSongs() {
        List<Song> mockSongs = List.of(testSong);

        try (MockedStatic<DataWriter> mockedStatic = mockStatic(DataWriter.class)) {
           
            mockedStatic.when(() -> DataWriter.saveSongs(mockSongs)).thenCallRealMethod();

            
            DataWriter.saveSongs(mockSongs);

          
            mockedStatic.verify(() -> DataWriter.saveSongs(mockSongs), times(1));
        }
    }

    @Test
    void testWriteToFileThrowsException() {
        try (MockedStatic<DataWriter> mockedStatic = mockStatic(DataWriter.class)) {
            mockedStatic.when(() -> DataWriter.saveUsers(anyList())).thenThrow(new RuntimeException("File write error"));

            Exception exception = assertThrows(RuntimeException.class, () -> DataWriter.saveUsers(List.of(testUser)));

            assertEquals("File write error", exception.getMessage());
        }
    }

}
