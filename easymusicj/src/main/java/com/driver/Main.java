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
                case 1 -> currentUser = createAccount(scanner);
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

    private static User createAccount(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists. Try again.");
                return null;
            }
        }

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
<<<<<<< HEAD
        break;

        case 2:
        System.out.print("Enter a song title to play: ");
        String songTitle = scanner.nextLine();
        Song selectedSong = new Song(songTitle, selectedInstrument, "C E G", true, new ArrayList<String>());
        System.out.println("You selected: " + selectedSong.getTitle());
            break;
        case 3:
            if (selectedInstrument == null) {
                System.out.println("Please select an instrument first (Option 1).");
                break;
            }
        
            System.out.print("Enter the name of the song you want to create: ");
            String createSongName = scanner.nextLine();
        
            String exampleNotes;
            switch (selectedInstrument.getType()) {
                case "Keyboard":
                    exampleNotes = "C, E, G (C Major Chord)";
                    break;
                case "String":
                    exampleNotes = "G, B, D (G Major Chord)";
                    break;
                case "Percussion":
                    exampleNotes = "Kick, Snare, Hi-Hat (Basic Beat)";
                    break;
                default:
                    exampleNotes = "C#, D#, F# (Example Notes)";
                    break;
            }
        
            System.out.println("Write your song! Example for " + selectedInstrument.getName() + ": " + exampleNotes);
            System.out.print("Enter your notes: ");
            String notes = scanner.nextLine();

            System.out.println("Playing your song...");
            Player player = new Player();
            player.play(notes);
        
            System.out.println("Do you want to save the song? (yes/no)");
            String saveChoice = scanner.nextLine().toLowerCase();
        
            if (saveChoice.equals("yes")) {
                Song createdSong = new Song(createSongName, selectedInstrument, notes, loggedIn, new ArrayList<String>());
                newUser.addComposedSong(createdSong);
                System.out.println("Song saved: " + createdSong.getTitle());
            } else {
                System.out.println("Song discarded.");
            }
        break;
        case 4:
            // Logout
            newUser.logOut();
            System.out.println("You have logged out.");
            loggedIn = false;
            break;

        default:
            System.out.println("Invalid choice. Please select a valid option.");
            break;
                }
            }
        } else {
            System.out.println("Invalid password. Please try again.");
        }
    } else {
        System.out.println("Username not found.");
=======
>>>>>>> 5d6bc60858a3f0f77b128372b9f357f773ca495c
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
    

    private static List<Instrument> createAvailableInstruments() {
        return Arrays.asList(
            new Instrument("Piano", "A classic keyboard instrument", null, null, "Keyboard"),
            new Instrument("Guitar", "A string instrument", null, null, "String"),
            new Instrument("Drums", "A percussion instrument", null, null, "Percussion"),
            new Instrument("Violin", "A bowed string instrument", null, null, "String")
        );
    }
}
