package com.driver;

import java.io.IOException;
import java.io.PrintWriter;
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
                case 2 -> currentUser = login(scanner, users);
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

    private static void preloadSongs() {
        List<Song> tomPettySongs = List.of(
            createSheetMusic("I Won’t Back Down", "Tom Petty", List.of("C", "D", "E", "F")),
            createSheetMusic("Free Fallin'", "Tom Petty", List.of("G", "C", "D")),
            createSheetMusic("Learning to Fly", "Tom Petty", List.of("F", "C", "Am", "G")),
            createSheetMusic("American Girl", "Tom Petty", List.of("D", "G", "A"))
        );

        for (Song song : tomPettySongs) {
            musicLibrary.addSong(song);
        }
    }

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
        SheetMusic sheetMusic = selectedSong.getSheetMusic();
    
        if (sheetMusic == null || sheetMusic.getMeasures().isEmpty()) {
            System.out.println("No sheet music available for this song.");
            return;
        }
    
        System.out.println("Now playing: " + selectedSong.getTitle() + " by " + selectedSong.getComposer());
        
        // Convert sheet music to JFugue pattern
        Pattern musicPattern = convertToJFugue(sheetMusic);
        
        // Play the song
        Player player = new Player();
        player.play(musicPattern);
    }
    
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

    private static void printSheetMusicToFile(SheetMusic sheetMusic) {
    if (sheetMusic == null) {
        System.out.println("Error: No sheet music available for this song.");
        return;
    }

    try (PrintWriter writer = new PrintWriter(sheetMusic.getTitle() +"_sheetmusic.txt")) {
        writer.println("========================================");
        writer.println("           " + sheetMusic.getTitle());
        writer.println("         By: " + sheetMusic.getComposer());
        writer.println("========================================");
        writer.println("Difficulty: " + sheetMusic.getDifficultyLevel());
        writer.println("Notation Type: " + sheetMusic.getNotationType());
        writer.println("Clef: " + sheetMusic.getClef());
        writer.println("Time Signature: " + sheetMusic.getTempoNumerator() + "/" + sheetMusic.getTempoDenominator());
        writer.println("Tempo: " + sheetMusic.getTempoNumerator() + " BPM");
        writer.println("----------------------------------------");

        int measureCount = 1;
        for (Measure measure : sheetMusic.getMeasures()) {
            writer.println("Measure " + measureCount + ":");
            writer.print("| ");
            for (Note note : measure.getNotes()) {
                writer.print(note.getPitch() + " (" + note.getDuration() + ") | ");
            }
            writer.println("\n----------------------------------------");
            measureCount++;
        }

        writer.println("========================================");
        System.out.println("Sheet music has been written to sheetmusic.txt.");
    } catch (IOException e) {
        System.out.println("Error writing sheet music to file: " + e.getMessage());
    }
}


    private static void createSong(Scanner scanner, User user) {
        System.out.print("Enter the name of your new song: ");
        String songName = scanner.nextLine();
        System.out.print("Enter your notes (comma-separated): ");
        String notesInput = scanner.nextLine();

        List<String> noteNames = Arrays.asList(notesInput.split(",\\s*"));
        ArrayList<Note> notes = new ArrayList<>();
        for (String noteName : noteNames) {
            notes.add(new Note(noteName, 1.0, 50)); // Adjust parameters as needed
        }

        Measure measure = new Measure(notes, 120, "4/4");
        ArrayList<Measure> measures = new ArrayList<>(List.of(measure));

        SheetMusic sheetMusic = new SheetMusic(UUID.randomUUID(), songName, user.getUsername(), "EASY", "STANDARD", 4, 4, "Treble", measures);
        Song newSong = new Song(UUID.randomUUID(), songName, songName, sheetMusic, false, new ArrayList<>());

        user.addComposedSong(newSong);
        songs.add(newSong);
        DataWriter.saveSongs(songs);
        DataWriter.saveUsers(users);

        System.out.println("Song saved: " + newSong.getTitle());
    }

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
    
    private static User login(Scanner scanner, List<User> users) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
    
        for (User user : users) {
            System.out.println("Checking user: " + user.getUsername()); // Debug line
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    user.setLoggedIn(true);
                    DataWriter.saveUsers(users); // Save updated login status to JSON
                    System.out.println("Logged in successfully!");
                    return user;
                } else {
                    System.out.println("Password mismatch for user: " + username);
                }
            }
        }
    
        System.out.println("Invalid username or password.");
        return null;
    }

    private static Pattern convertToJFugue(SheetMusic sheetMusic) {
        StringBuilder patternBuilder = new StringBuilder();
    
        for (Measure measure : sheetMusic.getMeasures()) {
            for (Note note : measure.getNotes()) {
                patternBuilder.append(note.getPitch()).append(note.getDuration()).append(" ");
            }
        }
    
        return new Pattern(patternBuilder.toString().trim());
    }
        
    
        
}

