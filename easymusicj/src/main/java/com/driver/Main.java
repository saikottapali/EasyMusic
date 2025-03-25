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
        
        User newUser = new User(id, username, password, email, firstName, lastName, 0, selectedInstrument, new ArrayList<Song>());

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
    System.out.println("4. Logout");

    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    switch (choice) {
        case 1:
            // Select instrument from the list
            List<Instrument> availableInstruments = createAvailableInstruments();

            System.out.println("Available Instruments:");
            for (int i = 0; i < availableInstruments.size(); i++) {
                System.out.println((i + 1) + ". " + availableInstruments.get(i).getName());
            }
    
            System.out.print("Select an instrument by entering the corresponding number: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
    
            if (choice >= 1 && choice <= availableInstruments.size()) {
                selectedInstrument = availableInstruments.get(choice - 1);
                System.out.println("You selected: " + selectedInstrument.getName());
            } else {
                System.out.println("Invalid selection.");
            }
            break;
        case 2:
            // Select song
            System.out.print("Enter a song title to play: ");
        String songTitle = scanner.nextLine();
        Song selectedSong = new Song(songTitle, songTitle, songTitle, null, null, loggedIn, null);  // This now works with new constructor
        System.out.println("You selected: " + selectedSong.getTitle());
            break;
            case 3:
            // Create song
            System.out.print("Enter the name of the song you want to create: ");
            String createSongName = scanner.nextLine();
            Song createdSong = new Song(createSongName, createSongName, createSongName, null, null, loggedIn, null); 
            newUser.addComposedSong(createdSong);
            System.out.println("You created the song: " + createdSong.getName());
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