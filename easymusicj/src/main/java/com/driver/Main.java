package com.driver;

import com.model.*;

import java.util.ArrayList;
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
                
            // Show user options
            boolean loggedIn = true;
            while (loggedIn) {
                System.out.println("\nWhat would you like to do?");
                System.out.println("1. Select an instrument");
                System.out.println("2. Select a song");
                System.out.println("3. Create a song");
                System.out.println("4. Logout");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        // Select instrument
                        System.out.print("Enter the instrument you want to select: ");
                        String instrumentName = scanner.nextLine();
                        Instrument selected = new Instrument(instrumentName); // Assuming you have an Instrument class
                        newUser.selectInstrument(selected);
                        System.out.println("You selected the instrument: " + selected.getName());
                        break;
                    case 2:
                        // Select song
                        System.out.print("Enter the name of the song you want to select: ");
                        String songName = scanner.nextLine();
                        Song selectedSong = new Song(songName); // Assuming you have a Song class
                        newUser.addComposedSong(selectedSong);
                        System.out.println("You selected the song: " + selectedSong.getName());
                        break;
                    case 3:
                        // Create song
                        System.out.print("Enter the name of the song you want to create: ");
                        String createSongName = scanner.nextLine();
                        Song createdSong = new Song(createSongName); // Assuming you have a Song class
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
}