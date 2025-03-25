package com.driver;

import com.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {

/*
* Account set up and login begins
*/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to EasyMusic! Let's create your account.");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        UUID id = UUID.randomUUID();

        Instrument selectedInstrument = null; 
        
        User newUser = new User(id, username, password, email, firstName, lastName, 0, null, new ArrayList<Song>());

        System.out.println("Account created successfully!");

        System.out.println("Now, let's log you in.");

        System.out.print("Enter your username: ");
        String enteredUsername = scanner.nextLine();

        if (enteredUsername.equals(newUser.getUsername())) {
            System.out.print("Enter password: ");
            String enteredPassword = scanner.nextLine();

            if (newUser.logIn(enteredPassword)) {
                System.out.println("Login successful!");

                newUser.practice(); 

                System.out.println("Practice streak: " + newUser.getPracticeStreak());
                
                System.out.println("You are now logged in and can continue to use the system.");

/*
* Account set up and login ends
*/
                
boolean loggedIn = true;
while (loggedIn) {
    System.out.println("\nWhat would you like to do?");
    System.out.println("1. Select an instrument");
    System.out.println("2. Select a song");
    System.out.println("3. Create a song");
    System.out.println("4. Play a note");
    System.out.println("5. Play a chord");
    System.out.println("4. Logout");

    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    switch (choice) {
        case 1:
        List<Instrument> availableInstruments = createAvailableInstruments();

        System.out.println("Available Instruments:");
        for (int i = 0; i < availableInstruments.size(); i++) {
            System.out.println((i + 1) + ". " + availableInstruments.get(i).getName());
        }

        System.out.print("Select an instrument by entering the corresponding number: ");
        int instrumentChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (instrumentChoice >= 1 && instrumentChoice <= availableInstruments.size()) {
             selectedInstrument = availableInstruments.get(instrumentChoice - 1);
            newUser.setInstrument(selectedInstrument);
            System.out.println("You selected: " + selectedInstrument.getName());
        } else {
            System.out.println("Invalid selection.");
        }
        break;

        case 2:
            // Select song
            System.out.print("Enter a song title to play: ");
        String songTitle = scanner.nextLine();
        Song selectedSong = new Song(songTitle, songTitle, songTitle, null, null, loggedIn, null);  
        System.out.println("You selected: " + selectedSong.getTitle());
            break;
            case 3:
            // Create song
            System.out.print("Enter the name of the song you want to create: ");
            String createSongName = scanner.nextLine();
            Song createdSong = new Song(createSongName, newUser.getInstrument(), null, loggedIn, new ArrayList<>());
            newUser.addComposedSong(createdSong);
            System.out.println("You created the song: " + createdSong.getName());
            break;  
        case 4:
            // Play a note
            if (newUser.getInstrument() != null) {
                System.out.print("Enter a note (e.g., C5): ");
                String pitch = scanner.nextLine();
                Note note = new Note(pitch, 1.0);
                newUser.getInstrument().play(note);
            } else {
                System.out.println("You must select an instrument first!");
            }
            break;
            case 5:
            // Play a chord
            if (newUser.getInstrument() != null) {
                System.out.print("Enter chord notes separated by spaces (e.g., C5 E5 G5): ");
                String[] pitches = scanner.nextLine().split(" ");
                List<Note> chordNotes = new ArrayList<>();
                for (String pitch : pitches) {
                    chordNotes.add(new Note(pitch, 1.0));
                }
                Chord chord = new Chord("Custom Chord", chordNotes);
                newUser.getInstrument().play(chord);
            } else {
                System.out.println("You must select an instrument first!");
            }
            break;

        case 6:
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
    }

    scanner.close();
}
private static List<Instrument> createAvailableInstruments() {
    List<Instrument> instruments = new ArrayList<>();
    instruments.add(new Instrument("Piano", "A classic keyboard instrument", null, null, "Keyboard"));
    instruments.add(new Instrument("Guitar", "A string instrument", null, null, "String"));
    instruments.add(new Instrument("Drums", "A percussion instrument", null, null, "Percussion"));
    instruments.add(new Instrument("Violin", "A bowed string instrument", null, null, "String"));
    return instruments;
}
}