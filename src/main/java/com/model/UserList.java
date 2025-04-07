package com.model;

import java.util.List;

/**
 * Singleton class that manages a list of users. The class provides methods to add,
 * remove, and retrieve users from the list, as well as a method to get all users.
 * This class ensures that only one instance of the list exists through the Singleton 
 * pattern, making it accessible globally in the application.
 */
public class UserList {
    
    private static UserList instance;  // Singleton instance of UserList
    private List<User> users;         // List that holds User objects
    
    /**
     * Private constructor to prevent direct instantiation from outside the class.
     * Initializes the list of users as an empty ArrayList.
     */
    private UserList() {
        users = DataLoader.loadUsers(); // Load users from DataLoader
    }

    /**
     * Returns the single instance of the UserList class. If the instance doesn't
     * exist, it is created.
     *
     * @return The singleton instance of the UserList.
     */
    public static UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    /**
     * Adds a user to the list of users.
     *
     * @param user The User object to be added to the list.
     */
    public void addUser(User user) {
        users.add(user);
        DataWriter.saveUsers(users); // Save the updated list of users to DataWriter
    }

    /**
     * Removes a user from the list by their username.
     * The username comparison is case-insensitive.
     *
     * @param username The username of the user to be removed.
     */
    public void removeUser(String username) {
        users.removeIf(user -> user.getUsername().equalsIgnoreCase(username));
        DataWriter.saveUsers(users);
    }

    /**
     * Retrieves a user by their username.
     * The username comparison is case-insensitive.
     *
     * @param username The username of the user to be retrieved.
     * @return The User object if found, or null if no user with the given username exists.
     */
    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        System.out.println("User not found: " + username);
        return null;
    }

    /**
     * Returns a list of all users.
     *
     * @return A new list containing all users in the system.
     */
    public List<User> getAllUsers() {
        return users;
    }
}