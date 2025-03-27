package com.driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.model.DataLoader;
import com.model.DataWriter;
import com.model.Measure;
import com.model.SheetMusic;
import com.model.Song;
import com.model.User;

public class Main {
    private static List<User> users = DataLoader.loadUsers();
    private static List<Song> songs = DataLoader.loadSongs();

    public static void main(String[] args) {
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

    private static User createAccount(Scanner scanner, List<User> users) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
    
        // Check if username already exists in the list of users
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists. Try again.");
                return null; // Return null to indicate failure in account creation
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

    private static void playSong(Scanner scanner) {
        System.out.print("Enter a song title to play: ");
        String songTitle = scanner.nextLine();

        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(songTitle)) {
                System.out.println("Now playing: " + song.getTitle());
                song.play();
                return;
            }
        }

        System.out.println("Song not found.");
    }

    private static void createSong(Scanner scanner, User user) {
        System.out.print("Enter the name of your new song: ");
        String songName = scanner.nextLine();
        System.out.print("Enter your notes (comma-separated): ");
        String notesInput = scanner.nextLine();
        
        // Convert input into a list of notes
        List<String> notes = Arrays.asList(notesInput.split(",\\s*")); 
    
        // Create a measure containing the notes
        Measure measure = new Measure(120, "4/4"); // Adjust tempo and time signature as needed
        List<Measure> measures = new ArrayList<>();
        measures.add(measure);
    
        // Create the SheetMusic object
        SheetMusic sheetMusic = new SheetMusic(UUID.randomUUID(), songName, user.getUsername(),
        "EASY", "STANDARD", 4, 4, "Treble", (ArrayList<Measure>) measures);
    
        // Create the Song object
        Song newSong = new Song(notesInput, notesInput, false, new ArrayList<>());
    
        user.addComposedSong(newSong);
        songs.add(newSong);
        DataWriter.saveSongs(songs);
        DataWriter.saveUsers(users);
    
        System.out.println("Song saved: " + newSong.getTitle());
    }
}
