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
                
            } else {
                System.out.println("Invalid password. Please try again.");
            }
        } else {
            System.out.println("Username not found.");
        }
/*
* Account set up and login end
*/
        
        scanner.close();
    }
}
