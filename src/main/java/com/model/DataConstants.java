package com.model;

public abstract class DataConstants {
    // User file paths
    protected static final String USER_FILE_NAME = "easymusicj/src/main/java/com/data/users.json"; //Don't change
    protected static final String USER_TEMP_FILE_NAME = "src/main/java/com/data/users_temp.json";

    // User attributes
    protected static final String USER_ID = "id";
    protected static final String USER_USER_NAME = "username";
    protected static final String USER_PASSWORD = "hashedPassword";
    protected static final String USER_EMAIL = "email";
    protected static final String USER_FIRST_NAME = "firstName";
    protected static final String USER_LAST_NAME = "lastName";
    protected static final String USER_PRACTICE_STREAK = "practiceStreak";
    protected static final String USER_SELECTED_INSTRUMENT = "selectedInstrument";
    protected static final String USER_COMPOSED_SONGS = "composedSongs";  // List of song IDs
    protected static final String USER_LOGGED_IN = "isLoggedIn";

    // Song file paths
    protected static final String SONG_FILE_NAME = "easymusicj/src/main/java/com/data/songs.json"; //Don't change
    protected static final String SONG_TEMP_FILE_NAME = "src/main/java/com/data/songs_temp.json";

    // Song attributes
    protected static final String SONG_ID = "id";
    protected static final String SONG_TITLE = "title";
    protected static final String SONG_COMPOSER = "composer";
    protected static final String SONG_DIFFICULTY = "difficultyLevel";
    protected static final String SONG_INSTRUMENT = "instrument";
    protected static final String SONG_DATE = "date";
    protected static final String SONG_SHEET_MUSIC = "sheetMusic";
    protected static final String SONG_IS_PRIVATE = "isPrivate";
    protected static final String SONG_COMMENTS = "comments"; // List of comments
    protected static final String SONG_NOTES = "songNotes";
    protected static final String SONG_TAGS = "tags";

    // Sheet Music attributes
    protected static final String SHEET_MUSIC_ID = "musicID";
    protected static final String SHEET_MUSIC_TITLE = "title";
    protected static final String SHEET_MUSIC_COMPOSER = "composer";
    protected static final String SHEET_MUSIC_DIFFICULTY = "difficultyLevel";
    protected static final String SHEET_MUSIC_NOTATION = "notationType";
    protected static final String SHEET_MUSIC_TEMPO_NUMERATOR = "tempoNumerator";
    protected static final String SHEET_MUSIC_TEMPO_DENOMINATOR = "tempoDenominator";
    protected static final String SHEET_MUSIC_CLEF = "clef";
    protected static final String SHEET_MUSIC_MEASURES = "measures"; // List of measures

    // Measure attributes
    protected static final String MEASURE_TEMPO = "tempo";
    protected static final String MEASURE_TIME_SIGNATURE = "timeSignature";
    protected static final String MEASURE_NOTES = "notes"; // List of notes

    // Note attributes
    protected static final String NOTE_PITCH = "pitch";
    protected static final String NOTE_DURATION = "duration";
}
