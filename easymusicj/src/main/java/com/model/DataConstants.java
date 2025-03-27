package com.model;

/**
 * This abstract class contains constants related to file paths and attribute names used
 * throughout the application for managing user, song, sheet music, measure, and note data.
 * These constants are intended to be used for data serialization/deserialization or 
 * file handling, and help maintain consistency across different parts of the application.
 */
public abstract class DataConstants {

    // User file paths
    /** The path to the main JSON file storing user data */
    protected static final String USER_FILE_NAME = "json/User.json";
    
    /** The path to a temporary JSON file for storing user data */
    protected static final String USER_TEMP_FILE_NAME = "src/main/java/com/data/users_temp.json";

    // User attributes
    /** The key for the user ID in the JSON data */
    protected static final String USER_ID = "id";
    
    /** The key for the username in the JSON data */
    protected static final String USER_USER_NAME = "username";
    
    /** The key for the hashed password in the JSON data */
    protected static final String USER_PASSWORD = "hashedPassword";
    
    /** The key for the user's email in the JSON data */
    protected static final String USER_EMAIL = "email";
    
    /** The key for the user's first name in the JSON data */
    protected static final String USER_FIRST_NAME = "firstName";
    
    /** The key for the user's last name in the JSON data */
    protected static final String USER_LAST_NAME = "lastName";
    
    /** The key for the user's practice streak in the JSON data */
    protected static final String USER_PRACTICE_STREAK = "practiceStreak";
    
    /** The key for the user's selected instrument in the JSON data */
    protected static final String USER_SELECTED_INSTRUMENT = "selectedInstrument";
    
    /** The key for the user's composed songs (list of song IDs) in the JSON data */
    protected static final String USER_COMPOSED_SONGS = "composedSongs";
    
    /** The key for whether the user is logged in in the JSON data */
    protected static final String USER_LOGGED_IN = "isLoggedIn";

    // Song file paths
    /** The path to the main JSON file storing song data */
    protected static final String SONG_FILE_NAME = "json/Song.json";
    
    /** The path to a temporary JSON file for storing song data */
    protected static final String SONG_TEMP_FILE_NAME = "src/main/java/com/data/songs_temp.json";

    // Song attributes
    /** The key for the song ID in the JSON data */
    protected static final String SONG_ID = "id";
    
    /** The key for the song title in the JSON data */
    protected static final String SONG_TITLE = "title";
    
    /** The key for the song composer in the JSON data */
    protected static final String SONG_COMPOSER = "composer";
    
    /** The key for the song's difficulty level in the JSON data */
    protected static final String SONG_DIFFICULTY = "difficultyLevel";
    
    /** The key for the song's instrument in the JSON data */
    protected static final String SONG_INSTRUMENT = "instrument";
    
    /** The key for the song's creation date in the JSON data */
    protected static final String SONG_DATE = "date";
    
    /** The key for the sheet music of the song in the JSON data */
    protected static final String SONG_SHEET_MUSIC = "sheetMusic";
    
    /** The key for whether the song is private in the JSON data */
    protected static final String SONG_IS_PRIVATE = "isPrivate";
    
    /** The key for the list of comments on the song in the JSON data */
    protected static final String SONG_COMMENTS = "comments";
    
    /** The key for the song notes in the JSON data */
    protected static final String SONG_NOTES = "songNotes";
    
    /** The key for the song tags in the JSON data */
    protected static final String SONG_TAGS = "tags";

    // Sheet Music attributes
    /** The key for the sheet music ID in the JSON data */
    protected static final String SHEET_MUSIC_ID = "musicID";
    
    /** The key for the sheet music title in the JSON data */
    protected static final String SHEET_MUSIC_TITLE = "title";
    
    /** The key for the sheet music composer in the JSON data */
    protected static final String SHEET_MUSIC_COMPOSER = "composer";
    
    /** The key for the sheet music difficulty level in the JSON data */
    protected static final String SHEET_MUSIC_DIFFICULTY = "difficultyLevel";
    
    /** The key for the notation type of the sheet music in the JSON data */
    protected static final String SHEET_MUSIC_NOTATION = "notationType";
    
    /** The key for the numerator of the tempo in the sheet music in the JSON data */
    protected static final String SHEET_MUSIC_TEMPO_NUMERATOR = "tempoNumerator";
    
    /** The key for the denominator of the tempo in the sheet music in the JSON data */
    protected static final String SHEET_MUSIC_TEMPO_DENOMINATOR = "tempoDenominator";
    
    /** The key for the clef of the sheet music in the JSON data */
    protected static final String SHEET_MUSIC_CLEF = "clef";
    
    /** The key for the list of measures in the sheet music in the JSON data */
    protected static final String SHEET_MUSIC_MEASURES = "measures";

    // Measure attributes
    /** The key for the tempo of the measure in the JSON data */
    protected static final String MEASURE_TEMPO = "tempo";
    
    /** The key for the time signature of the measure in the JSON data */
    protected static final String MEASURE_TIME_SIGNATURE = "timeSignature";
    
    /** The key for the list of notes in the measure in the JSON data */
    protected static final String MEASURE_NOTES = "notes";

    // Note attributes
    /** The key for the pitch of the note in the JSON data */
    protected static final String NOTE_PITCH = "pitch";
    
    /** The key for the duration of the note in the JSON data */
    protected static final String NOTE_DURATION = "duration";
}
