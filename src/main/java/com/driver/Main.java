package com.driver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

import com.model.DataLoader;
import com.model.DataWriter;
import com.model.Measure;
import com.model.MusicLibrary;
import com.model.Note;
import com.model.SheetMusic;
import com.model.Song;
import com.model.User;

public class Main {
    private static List<User> users = DataLoader.loadUsers();
    private static List<Song> songs = DataLoader.loadSongs();
    private static MusicLibrary musicLibrary = new MusicLibrary();

    /**
     * Main method to run the EasyMusic app.
     * Handles user login and main menu.
     */
    public static void main(String[] args) {
        preloadSongs();
        Scanner scanner = new Scanner(System.in);
        User currentUser = null;

        System.out.println("Welcome to EasyMusic!");
        while (currentUser == null) {
            System.out.println("1. Create an account");
            System.out.println("2. Log in");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (option) {
                case 1 -> currentUser = createAccount(scanner, users);
                case 2 -> currentUser = login(scanner);
                default -> System.out.println("Invalid option. Try again.");
            }
        }

        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Play a song");
            System.out.println("2. Create a song");
            System.out.println("3. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> playSong(scanner);
                case 2 -> createSong(scanner, currentUser);
                case 3 -> {
                    System.out.println("You have logged out.");
                    currentUser.logOut();
                    DataWriter.saveUsers(users);
                    loggedIn = false;
                }
                default -> System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        scanner.close();
    }

    /**
     * Preload a few songs into the music library for demonstration.
     */
    private static void preloadSongs() {
        List<Song> tomPettySongs = List.of(
            createSheetMusic("I Won’t Back Down", "Tom Petty", List.of("C", "D", "E", "F")),
            createSheetMusic("Free Fallin'", "Tom Petty", List.of("G", "C", "D")),
            createSheetMusic("Learning to Fly", "Tom Petty", List.of("F", "C", "Am", "G")),
            createSheetMusic("American Girl", "Tom Petty", List.of("D", "G", "A"))
        );
    
        for (Song song : tomPettySongs) {
            musicLibrary.addSong(song);
            songs.add(song); // Add to songs list so it appears in playSong()
        }
    }

    /**
     * Creates sheet music for a song based on the provided title, artist, and note names.
     *
     * @param title The title of the song.
     * @param artist The artist of the song.
     * @param noteNames List of note names for the song.
     * @return The created Song object.
     */
    private static Song createSheetMusic(String title, String artist, List<String> noteNames) {
        ArrayList<Note> notes = new ArrayList<>();
        for (String noteName : noteNames) {
            notes.add(new Note(noteName, 0, 0)); // Adjust parameters as needed
        }
    
        Measure measure = new Measure(notes, 120, "4/4");
        ArrayList<Measure> measures = new ArrayList<>(List.of(measure));
    
        SheetMusic sheetMusic = new SheetMusic(UUID.randomUUID(), title, artist, "EASY", "STANDARD", 4, 4, "Treble", measures);
        return new Song(UUID.randomUUID(), title, artist, sheetMusic ,false, new ArrayList<>());
    }

    /**
     * Allows the user to play a selected song from the available list of songs.
     * The song is played using JFugue if it is not Free Fallin'.
     *
     * @param scanner The scanner to take user input.
     */
    private static void playSong(Scanner scanner) {
        System.out.println("Available Songs:");
        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i + 1) + ". " + songs.get(i).getTitle());
        }
    
        System.out.print("Choose a song to play: ");
        int songIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline
    
        if (songIndex < 1 || songIndex > songs.size()) {
            System.out.println("Invalid selection.");
            return;
        }
    
        Song selectedSong = songs.get(songIndex - 1);
        System.out.println("Now playing: " + selectedSong.getTitle());
        
        // Call the play method to actually play the song
        selectedSong.play();  // This will trigger the play method in the Song class
    
    

        // Hardcoded playback for Free Fallin'
        if (selectedSong.getTitle().equalsIgnoreCase("Free Fallin'")) {
            playFreeFallin();
        } else {
            playWithJFugue(selectedSong);
        }

        // Print sheet music after playing
        printSheetMusicToFile(selectedSong.getSheetMusic());
    }

    /**
     * Plays a song using the JFugue library, converting its notes into a musical pattern.
     *
     * @param song The song to be played.
     */
    private static void playWithJFugue(Song song) {
        if (song.getSheetMusic() == null || song.getSheetMusic().getMeasures() == null) {
            System.out.println("Error: Sheet music not available for this song.");
            return;
        }

        StringBuilder patternString = new StringBuilder();
        for (Measure measure : song.getSheetMusic().getMeasures()) {
            for (Note note : measure.getNotes()) {
                patternString.append(note).append(" ");
            }
            patternString.append(" | ");
        }

        Pattern pattern = new Pattern(patternString.toString());
        Player player = new Player();
        player.play(pattern);
    }

    /**
     * Prints the sheet music of a song to a file.
     * The file is saved as a .txt file with the song title.
     *
     * @param sheetMusic The sheet music to be saved.
     */
    private static void printSheetMusicToFile(SheetMusic sheetMusic) {
        try {
            // Open a file writer
            FileWriter fileWriter = new FileWriter(sheetMusic.getTitle()+"_sheet_music.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    
            // Hardcode the song details
            bufferedWriter.write("========================================\n");
            bufferedWriter.write("           " + sheetMusic.getTitle() + "\n");
            bufferedWriter.write("         By: " + sheetMusic.getComposer() + "\n");
            bufferedWriter.write("========================================\n");
            bufferedWriter.write("Difficulty: " + sheetMusic.getDifficultyLevel() + "\n");
            bufferedWriter.write("Notation Type: " + sheetMusic.getNotationType() + "\n");
            bufferedWriter.write("Clef: " + sheetMusic.getClef() + "\n");
            bufferedWriter.write("Time Signature: " + sheetMusic.getTempoNumerator() + "/" + sheetMusic.getTempoDenominator() + "\n");
            bufferedWriter.write("Tempo: " + sheetMusic.getTempoNumerator() + " BPM\n");
            bufferedWriter.write("----------------------------------------\n");
    
            // Hardcoded example measures and notes
            bufferedWriter.write("Measure 1:\n");
            bufferedWriter.write("| G (1.0) | C (1.0) | D (1.0) | G (1.0) |\n");
            bufferedWriter.write("----------------------------------------\n");
    
            bufferedWriter.write("Measure 2:\n");
            bufferedWriter.write("| C (1.0) | D (1.0) | G (1.0) | C (1.0) |\n");
            bufferedWriter.write("----------------------------------------\n");
    
            bufferedWriter.write("Measure 3:\n");
            bufferedWriter.write("| D (1.0) | G (1.0) | C (1.0) | D (1.0) |\n");
            bufferedWriter.write("----------------------------------------\n");
    
            // Close the writer
            bufferedWriter.close();
            fileWriter.close();
            System.out.println("Sheet music written to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new song by taking user input for song title and notes.
     * The new song is saved and added to the user's song list.
     *
     * @param scanner The scanner to take user input.
     * @param user The user who is creating the song.
     */
    private static void createSong(Scanner scanner, User user) {
        System.out.print("Enter the name of your new song: ");
        String songName = scanner.nextLine();
        System.out.print("Enter your notes (comma-separated): ");
        String notesInput = scanner.nextLine();
    
        List<String> noteNames = Arrays.asList(notesInput.split(",\\s*"));
        ArrayList<Note> notes = new ArrayList<>();
        for (String noteName : noteNames) {
            notes.add(new Note(noteName, 1.0, 50)); 
        }
    
        Measure measure = new Measure(notes, 120, "4/4");
        ArrayList<Measure> measures = new ArrayList<>(List.of(measure));
    
        SheetMusic sheetMusic = new SheetMusic(UUID.randomUUID(), songName, user.getUsername(), "EASY", "STANDARD", 4, 4, "Treble", measures);
        Song newSong = new Song(UUID.randomUUID(), songName, songName, sheetMusic, false, new ArrayList<>());
    
        user.addComposedSong(newSong);
        songs.add(newSong);
        DataWriter.saveSongs(songs);
        DataWriter.saveUsers(users);
    
        System.out.println("Playing song...");
        System.out.println("Song saved: " + newSong.getTitle());

    
        // Play the song
        playSong(notesInput);
    }
    
    private static void playSong(String notesInput) {
        Player player = new Player();
        player.play(notesInput);  // Using the notes input as a string to play the song
        
    }

    /**
     * Creates a new user account after checking for existing users.
     *
     * @param scanner The scanner to take user input.
     * @param users List of existing users.
     * @return The newly created user.
     */
    private static User createAccount(Scanner scanner, List<User> users) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        
        // Check if username already exists in the list of users
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists. Try again.");
                return null;  // Return null to indicate failure in account creation
            }
        }
        
        // If no existing user, proceed with account creation
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
    
        UUID id = UUID.randomUUID();
        User newUser = new User(id, username, password, email, firstName, lastName, 0, new ArrayList<>(), false);
        users.add(newUser);
        DataWriter.saveUsers(users);
    
        System.out.println("Account created successfully!");
        return newUser;
    }

    /**
     * Allows the user to log in to the app by providing their username and password.
     *
     * @param scanner The scanner to take user input.
     * @return The logged-in user.
     */
    private static User login(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
    
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
    
                if (user.logIn(password)) {
                    System.out.println("Login successful!");
                    return user;
                } else {
                    System.out.println("Incorrect password.");
                    return null;
                }
            }
        }
    
        System.out.println("Username not found.");
        return null;
    }
    
    /**
     * Plays the song "Free Fallin'" using a hardcoded pattern.
     */
    private static void playFreeFallin() {
        Player player = new Player();
    
        // Define the song pattern for Free Fallin'
        String songPattern = "Gq Cq Dq Gq Cq Dq Gq Cq Dq Gq Cq Dq | Gh";
    
        // Play the song using JFugue
        player.play(songPattern);
    }
}
