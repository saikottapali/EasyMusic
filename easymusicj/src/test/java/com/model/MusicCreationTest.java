package com.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MusicCreationTest {

    private MusicCreation musicCreation;

    @BeforeEach
    void setUp() {
        musicCreation = new MusicCreation();
    }

    @Test
    void testConstructorInitialization() {
        assertNotNull(musicCreation.getSheetMusic());
        assertTrue(musicCreation.getSheetMusic().isEmpty());
    }

    // @Test
    // void testCreateMusic() {
    //     // Prepare dependencies
    //     List<String> songNotes = new ArrayList<>();
    //     ArrayList<Measure> measures = new ArrayList<>();
    //     List<SheetMusic> sheetMusic = new ArrayList<>();
    //     songNotes.add(new Note("C4", "h"));
    //     songNotes.add(new Note("D4", "q"));
    //     measures.add(new Measure((songNotes, 120, "4/4")));
        
    //     // Create a song
    //     Song song = musicCreation.createMusic("Test Song", "Test Composer", "Easy", new Date(),
    //         false, songNotes, sheetMusic)

    //     // Verify the song properties
    //     assertNotNull(song);
    //     assertEquals("Test Song", song.getTitle());
    //     assertEquals("Test Composer", song.getComposer());
    //     assertEquals("Easy", song.getDifficultyLevel());
    //     equals(song.getSheetMusic());
    //     equals(song.getSongNotes());
    //     assertEquals(false, song.isPrivate());
    //     assertEquals(new Date(), song.getDate());
    // }
}

    //@Test
    // void testPlaySongWithValidSong() {
    //     // Prepare dependencies
    //     SheetMusic sheetMusic = new SheetMusic();
    //     Song song = new Song("1", "Test Song", "Test Composer", sheetMusic, false, new ArrayList<>());
    //     Note note = new Note("C4","h");

    //     // Add the song to the list
    //     musicCreation.getSheetMusic().add(sheetMusic);
    //     musicCreation.playSong(song, note);

    //     // Verify play behavior (you can add assertions if `Note` has state changes)
    //     System.out.println("Song played successfully.");
    // }

    // @Test
    // void testPlaySongWithInvalidSong() {
    //     // Prepare dependencies
    //     Song song = new Song("1", "Invalid Song", "Unknown Composer", null, false, new ArrayList<>());
    //     Note note = new Note();

    //     // Attempt to play a song not in the list
    //     musicCreation.playSong(song, note);

    //     // Verify no interaction (you can add assertions if `Note` has state changes)
    //     System.out.println("Song not found, cannot play.");
    // }

//     @Test
//     void testSetAndGetSheetMusic() {
//         // Prepare a new sheet music list
//         ArrayList<SheetMusic> newSheetMusicList = new ArrayList<>();
//         newSheetMusicList.add(new SheetMusic());

//         // Set the new sheet music list
//         musicCreation.setSheetMusic(newSheetMusicList);

//         // Verify the getter returns the updated list
//         assertEquals(newSheetMusicList, musicCreation.getSheetMusic());
//     }

//     @Test
//     void testCreateMusicWithEmptyNotes() {
//         // Prepare dependencies
//         SheetMusic sheetMusic = new SheetMusic(null, null, null, null, 0, 0, null, null);
//         ArrayList<String> songNotes = new ArrayList<>();

//         // Create a song with empty notes
//         Song song = musicCreation.createMusic("Empty Notes Song", "Composer", "Medium", 
//         new Date(),true, null, sheetMusic);

//         // Verify the song properties
//         assertNotNull(song);
//         assertEquals("Empty Notes Song", song.getTitle());
//         assertTrue(song.getSongNotes().isEmpty());
//     }
// }
//         // Verify the song properties
//         assertNotNull(song);
//         assertEquals("Test Song", song.getTitle());
//         assertEquals("Test Composer", song.getComposer());
//         assertEquals(songNotes, song.getSongNotes());
//         assertFalse(song.isPrivate());

//         // Verify that the song is saved to the sheet music
//         verify(mockSheetMusic).saveToFile(song);
// }