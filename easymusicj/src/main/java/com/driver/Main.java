package com.driver;

import java.util.Scanner;

import com.model.EasyMusicFacade;

public class Main {
    private static EasyMusicFacade facade = new EasyMusicFacade(); // Use the facade

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to EasyMusic!");

        // Loop until the user is logged in
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("1. Create an account");
            System.out.println("2. Log in");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1 -> loggedIn = createAccount(scanner);
                case 2 -> loggedIn = logIn(scanner);
                default -> System.out.println("Invalid option. Try again.");
            }
        }

        boolean running = true;
        while (running) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Play a song");
            System.out.println("2. Create a song");
            System.out.println("3. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> playSong(scanner);
                case 2 -> createSong(scanner);
                case 3 -> {
                    System.out.println("You have logged out.");
                    facade.logOut();
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        scanner.close();
    }

    /**
     * Creates a new account for the user.
     * @param scanner Scanner object to read user input
     * @return true if account creation was successful, false otherwise
     */
    private static boolean createAccount(Scanner scanner) {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        boolean success = facade.createAccount(firstName, lastName, username, password, email);
        if (success) {
            System.out.println("Account created successfully!");
            return true;
        } else {
            System.out.println("Failed to create account. Username may already exist.");
            return false;
        }
    }

    /**
     * Logs in the user with the provided username and password.
     * @param scanner
     * @return true if login was successful, false otherwise
     */
    private static boolean logIn(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

<<<<<<< HEAD
        System.out.print("Enter password: ");
        String password = scanner.nextLine();


        for (User user : users) {
            if (user.getUsername().equals(username)) {
                
                if (user.getHashedPassword().equals(password)) {
                    user.setLoggedIn(true);
                    return user;
                } else {
                    System.out.println("Incorrect password.");
                    return null;
                }
            }
=======
        boolean success = facade.logIn(username, password);
        if (success) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }

    /**
     * Plays a song selected by the user.
     * @param scanner
     */
    private static void playSong(Scanner scanner) {
        System.out.println("Available Songs:");
        String[] songTitles = facade.getAvailableSongs();
        for (int i = 0; i < songTitles.length; i++) {
            System.out.println((i + 1) + ". " + songTitles[i]);
>>>>>>> da734aa221eb10796a810c7a006516eb865aba7a
        }

        System.out.print("Choose a song to play: ");
        int songIndex = scanner.nextInt();
        scanner.nextLine(); 

        if (songIndex < 1 || songIndex > songTitles.length) {
            System.out.println("Invalid selection.");
            return;
        }

        String songTitle = songTitles[songIndex - 1];
        boolean success = facade.playSong(songTitle);
        if (!success) {
            System.out.println("Failed to play the song. Sheet music may be missing.");
        }
    }

    /**
     * Creates a new song with the provided details.
     * @param scanner
     */
    private static void createSong(Scanner scanner) {
        System.out.print("Enter the name of your new song: ");
        String songName = scanner.nextLine();
        System.out.print("Enter the difficulty level (e.g., EASY, MEDIUM, HARD): ");
        String difficultyLevel = scanner.nextLine();
        System.out.print("Is the song private? (true/false): ");
        boolean isPrivate = scanner.nextBoolean();
        scanner.nextLine();
        System.out.print("Enter your notes (comma-separated): ");
        String notesInput = scanner.nextLine();

        boolean success = facade.createSong(songName, difficultyLevel, isPrivate, notesInput);
        if (success) {
            System.out.println("Song created successfully!");
        } else {
            System.out.println("Failed to create the song.");
        }
    }

}
