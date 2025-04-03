package com.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Savanna Welch

class UserListTest {

    private UserList userList;
    private DataLoader mockDataLoader;
    private DataWriter mockDataWriter;
    
    @BeforeEach
    void setUp() {
        // Mock DataLoader and DataWriter to isolate the tests from actual file operations
        mockDataLoader = mock(DataLoader.class);
        mockDataWriter = mock(DataWriter.class);
        
        // Mock loadUsers() to return a predefined list of users
        List<User> predefinedUsers = Arrays.asList(
            new User(null, "user1", "password1", "email1@example.com", null, null, 0, null, false),
            new User(null, "user2", "password2", "email2@example.com", null, null, 0, null, false)
        );
        when(mockDataLoader.loadUsers()).thenReturn(predefinedUsers);
        
        // Initialize UserList instance (assuming it uses DataLoader and DataWriter internally)
        userList = UserList.getInstance();
    }

    @Test
    void testGetInstance() {
        // Test if the singleton pattern works correctly
        UserList userList1 = UserList.getInstance();
        UserList userList2 = UserList.getInstance();
        
        assertSame(userList1, userList2, "Both instances should be the same.");
    }

    @Test
    void testAddUser() {
        // Create a new user to add to the list
        User newUser = new User(null, "user3", "password3", "email3@example.com", null, null, 0, null, false);
        userList.addUser(newUser);
        
        // Verify the user was added to the list
        List<User> users = userList.getAllUsers();
        assertTrue(users.contains(newUser), "The new user should be added to the list.");
        
        // Verify that DataWriter.saveUsers was called
        verify(mockDataWriter, times(1)).saveUsers(users);
    }

    @Test
    void testRemoveUser() {
        // Remove a user from the list
        userList.removeUser("user1");
        
        // Verify the user was removed from the list
        List<User> users = userList.getAllUsers();
        assertFalse(users.stream().anyMatch(user -> user.getUsername().equals("user1")),
                "The user1 should be removed from the list.");
        
        // Verify that DataWriter.saveUsers was called after removal
        verify(mockDataWriter, times(1)).saveUsers(users);
    }

    @Test
    void testGetUser() {
        // Retrieve a user by username
        User user = userList.getUser("user2");
        
        // Verify that the correct user is returned
        assertNotNull(user, "The user should not be null.");
        assertEquals("user2", user.getUsername(), "The returned user should have username 'user2'.");
        
        // Try to retrieve a non-existent user
        User nonExistentUser = userList.getUser("nonexistentuser");
        
        // Verify that the non-existent user returns null
        assertNull(nonExistentUser, "The non-existent user should return null.");
    }

    @Test
    void testGetAllUsers() {
        // Test getting all users in the system
        List<User> users = userList.getAllUsers();
        
        // Verify that the list is not empty and contains the correct number of users
        assertNotNull(users, "The users list should not be null.");
        assertEquals(2, users.size(), "The users list should contain 2 users initially.");
    }
}

