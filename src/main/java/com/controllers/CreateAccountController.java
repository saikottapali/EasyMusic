package com.controllers;

import java.io.IOException;

import com.model.EasyMusicFacade;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateAccountController {


    @FXML
    private Button btn_cancel;

    @FXML
    private Button btn_create;

    @FXML
    private TextField txt_first;
    
    @FXML
    private TextField txt_last;

    @FXML
    private TextField txt_email;
    
    @FXML
    private TextField txt_username;

    @FXML
    private PasswordField txt_password;

    @FXML
    private Label account_message;


    @FXML
    private void createUser() throws IOException {
    
        checkUserCreation();
    }

    @FXML
    private void checkUserCreation() throws IOException {
        String firstName = txt_first.getText();
        String lastName = txt_last.getText();
        String email = txt_email.getText();
        String username = txt_username.getText();
        String password = txt_password.getText();


        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            account_message.setText("Please fill in all fields.");
            return;
        }
        else if (!firstName.chars().allMatch(Character::isLetter)) {
            account_message.setText("First name must contain only letters.");
            return;
        }
        else if (!lastName.chars().allMatch(Character::isLetter)) {
            account_message.setText("Last name must contain only letters.");
            return;
        }
        else if (!email.contains("@")) {
            account_message.setText("Email must contain '@'.");
            return;
        }

        EasyMusicFacade facade = new EasyMusicFacade();
        if (facade.createAccount(firstName, lastName, email, username, password)) {
            App.setRoot("login");
        } else {
            account_message.setText("Username already exists.");
        }
    }
    

    @FXML
    private void cancelButton() throws IOException {
        
        App.setRoot("login");
        
    }


}
