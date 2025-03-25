package com.model;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    private static UserList instance; // Singleton instance
    private List<User> users;         // List of users

    // Private constructor to prevent direct instantiation
    private UserList() {
        this.users = new ArrayList<>();
    }

    // Public method to get the single instance
    public static UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    // Add a user
    public void addUser(User user) {
        users.add(user);
        System.out.println("User added: " + user.getUsername());
    }

    // Remove a user by username
    public void removeUser(String username) {
        users.removeIf(user -> user.getUsername().equalsIgnoreCase(username));
        System.out.println("User removed: " + username);
    }

    // Get a user by username
    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        System.out.println("User not found: " + username);
        return null;
    }

    // Get all users
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}
