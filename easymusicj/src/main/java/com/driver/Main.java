package com.driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.model.DataLoader;
import com.model.DataWriter;
import com.model.Instrument;
import com.model.Measure;
import com.model.SheetMusic;
import com.model.Song;
import com.model.User;

/**
 * Main class that runs the EasyMusic application. This class handles user authentication,
 * allows users to select instruments, play songs, and create new songs. It provides a 
 * console-based interface for interacting with the system, utilizing the Singleton pattern 
 * to load and save users and songs.
 */
public class Main {
    
    // List of users and songs, loaded from data source
    private static List<User> users = DataLoader.loadUsers();
    private static List<Song> songs = DataLoader.loadSongs();

    /**
     * Main entry point for the EasyMusic application. This method provides a console 
     * interface for users to create an account, log in, select instruments, play songs, 
     * and create new songs.
     *
     * @param args Command line arguments (not used).
     */
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
                case 2 -> currentUser = login(scanner);
                default -> System.out.println("Invalid option. Try again.");
            }
        }

        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Select an instrument");
            System.out.println("2. Play a song");
            System.out.println("3. Create a song");
            System.out.println("4. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> selectInstrument(scanner, currentUser);
                case 2 -> playSong(scanner);
                case 3 -> createSong(scanner, currentUser);
                case 4 -> {
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
     * Prompts the user to create a new account. The account is created if the username is unique.
     *
     * @param scanner The scanner object for reading input.
     * @param users The list of users in the system.
     * @return The newly created User, or null if account creation failed.
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
        User newUser = new User(id, username, password, email, firstName, lastName, 0, null, new ArrayList<>(), false);
        users.add(newUser);
        DataWriter.saveUsers(users);
    
        System.out.println("Account created successfully!");
        return newUser;
    }

    /**
     * Prompts the user to log in by entering a username and password.
     *
     * @param scanner The scanner object for reading input.
     * @return The User object if login is successful, or null if the login failed.
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
     * Allows the user to select an instrument from a predefined list of available instruments.
     *
     * @param scanner The scanner object for reading input.
     * @param user The current User who is selecting an instrument.
     */
    private static void selectInstrument(Scanner scanner, User user) {
        List<Instrument> availableInstruments = createAvailableInstruments();
        System.out.println("Available Instruments:");
        for (int i = 0; i < availableInstruments.size(); i++) {
            System.out.println((i + 1) + ". " + availableInstruments.get(i).getName());
        }

        System.out.print("Select an instrument by entering the corresponding number: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice >= 1 && choice <= availableInstruments.size()) {
            Instrument selectedInstrument = availableInstruments.get(choice - 1);
            user.selectInstrument(selectedInstrument);
            System.out.println("You selected: " + selectedInstrument.getName());
        } else {
            System.out.println("Invalid selection.");
        }
    }

    /**
     * Allows the user to play a song by entering its title.
     *
     * @param scanner The scanner object for reading input.
     */
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

    /**
     * Allows the user to create a new song by entering its title and notes. The song is saved 
     * along with its sheet music and added to the user's composed songs.
     *
     * @param scanner The scanner object for reading input.
     * @param user The current User creating the song.
     */
    private static void createSong(Scanner scanner, User user) {
        if (user.getSelectedInstrument() == null) {
            System.out.println("Please select an instrument first.");
            return;
        }
    
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
        Song newSong = new Song(UUID.randomUUID(), songName, user.getUsername(), "EASY", 
            user.getSelectedInstrument(), new Date(), sheetMusic, 
            false, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    
        user.addComposedSong(newSong);
        songs.add(newSong);
        DataWriter.saveSongs(songs);
        DataWriter.saveUsers(users);
    
        System.out.println("Song saved: " + newSong.getTitle());
    }
    
    /**
     * Creates a list of available instruments.
     *
     * @return A list of Instrument objects available for selection.
     */
    private static List<Instrument> createAvailableInstruments() {
        return Arrays.asList(
            new Instrument("Piano", "A classic keyboard instrument", null, null, "Keyboard"),
            new Instrument("Guitar", "A string instrument", null, null, "String"),
            new Instrument("Drums", "A percussion instrument", null, null, "Percussion"),
            new Instrument("Violin", "A bowed string instrument", null, null, "String")
        );
    }
}
