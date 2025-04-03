package com.model;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.MockedStatic;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
* @author Savanna Welch
*/

class DataWriterTest {

    @TempDir
    Path tempDir; 

    private User testUser;
    private Song testSong;
    private SheetMusic testSheetMusic;

    @BeforeEach
    void setUp() {
        
        User testUser = mock(User.class);

        
        when(testUser.getUsername()).thenReturn("testUser");
        when(testUser.getPassword()).thenReturn("testPassword");
        

        SheetMusic testSheetMusic = new SheetMusic();  
        ArrayList<String> testNotes = new ArrayList<>();
        testNotes.add("Note 1");
        testNotes.add("Note 2");
        
       
        testSong = new Song("Test Song", "Composer", "Medium", testSheetMusic, false, testNotes);
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
