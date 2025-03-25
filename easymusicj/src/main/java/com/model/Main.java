package com.driver;

import com.model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create an Instructor
        Instructor instructor = new Instructor("musicMaster", "securePass", "instructor@example.com", "Alice", "Smith", new ArrayList<>());

        // Create a Student
        Student student = new Student("johnDoe", "pass123", "john@example.com", "John", "Doe", new ArrayList<>(), instructor);

        // User tries to log in
        System.out.println("Enter password for " + student.getUsername() + ": ");
        String enteredPassword = scanner.nextLine();
        
        if (student.logIn(enteredPassword)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed. Incorrect password.");
            scanner.close();
            return;  // Exit program if login fails
        }

        // User logged in successfully
        System.out.println("\n=== Account Actions ===");
        System.out.println(student.getUsername() + " is now logged in.");
        
        // Profile Update
        System.out.println("\nUpdating profile...");
        student.updateProfile("newEmail@example.com", "newSecurePass");
        System.out.println("Profile updated!");

        // Practice Session
        System.out.println("\nPracticing...");
        student.practice();
        System.out.println("Practice streak: " + 1);  // Manually tracking in driver

        // Log Out
        student.logOut();
        System.out.println("\nLogged out!");

        scanner.close();
    }
}
