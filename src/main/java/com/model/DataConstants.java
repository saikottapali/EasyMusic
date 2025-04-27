package com.model;

public abstract class DataConstants {
    // User file paths
    protected static final String USER_FILE_NAME = "src/main/java/com/data/users.json"; //Don't change
    protected static final String USER_TEMP_FILE_NAME = "src/main/java/com/data/users_temp.json";

    // User attributes
    protected static final String USER_ID = "id"; // UUID
    protected static final String USER_USER_NAME = "username"; // Unique username
    protected static final String USER_PASSWORD = "password"; // Password
    protected static final String USER_EMAIL = "email"; // Email address
    protected static final String USER_FIRST_NAME = "firstName"; // First name
    protected static final String USER_LAST_NAME = "lastName"; // Last name
    protected static final String USER_COMPOSED_SONGS = "composedSongs";  // List of song IDs
    protected static final String USER_LOGGED_IN = "isLoggedIn"; // Boolean indicating if the user is logged in

    // Song file paths
    protected static final String SONG_FILE_NAME = "src/main/java/com/data/songs.json"; //Don't change
    protected static final String SONG_TEMP_FILE_NAME = "src/main/java/com/data/songs_temp.json"; 

    // Song attributes
    protected static final String SONG_ID = "id"; // UUID
    protected static final String SONG_TITLE = "title"; // Title of the song
    protected static final String SONG_COMPOSER = "composer"; // Composer of the song
    protected static final String SONG_DIFFICULTY = "difficultyLevel"; // Difficulty level (e.g., easy, medium, hard)
    protected static final String SONG_DATE = "date"; // Date of creation
    protected static final String SONG_SHEET_MUSIC = "sheetMusic"; // List of sheet music IDs
    protected static final String SONG_IS_PRIVATE = "isPrivate"; // Boolean indicating if the song is private
    protected static final String SONG_NOTES = "songNotes"; // List of notes in the song

    // Sheet Music attributes
    protected static final String SHEET_MUSIC_ID = "musicID"; // UUID
    protected static final String SHEET_MUSIC_TITLE = "title"; // Title of the sheet music
    protected static final String SHEET_MUSIC_COMPOSER = "composer"; // Composer of the sheet music
    protected static final String SHEET_MUSIC_DIFFICULTY = "difficultyLevel"; // Difficulty level (e.g., easy, medium, hard)
    protected static final String SHEET_MUSIC_TEMPO = "tempo"; // Tempo of the sheet music
    protected static final String SHEET_MUSIC_CLEF = "clef"; // Clef of the sheet music (e.g., treble, bass)
    protected static final String SHEET_MUSIC_MEASURES = "measures"; // List of measures

    // Measure attributes
    protected static final String MEASURE_TEMPO = "tempo"; // Tempo of the measure
    protected static final String MEASURE_TIME_SIGNATURE = "timeSignature"; // Time signature of the measure (e.g., 4/4, 3/4)
    protected static final String MEASURE_NOTES = "notes"; // List of notes

    // Note attributes
    protected static final String NOTE_NAME = "name"; // e.g., C, D, E, F, G, A, B
    protected static final String NOTE_PITCH = "pitch"; // e.g., sharp, flat
    protected static final String NOTE_OCTAVE = "octave"; // e.g., 4
    protected static final String NOTE_DURATION = "duration"; // e.g., q for quarter note, h for half note, w for whole note
}
