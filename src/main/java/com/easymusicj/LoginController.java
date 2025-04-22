package com.easymusicj;

import java.io.IOException;

import com.model.EasyMusicFacade;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_cancel;

    @FXML
    private Button btn_account;

    @FXML
    private TextField txt_username;

    @FXML
    private PasswordField txt_password;

    @FXML
    private Label login_message;

    private EasyMusicFacade facade;

    public LoginController() {
        facade = EasyMusicFacade.getInstance();
    }

    @FXML
    private void userLogin() throws IOException {
    
        checkLogin();

    }

    @FXML
    private void checkLogin() throws IOException {
        String usernameInput = txt_username.getText();
        String passwordInput = txt_password.getText();

        if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
            login_message.setText("Please enter username and password.");
            return;
        }

        facade.login(usernameInput, passwordInput);  // This should set the currentUser in facade
        
        if (facade.getCurrentUser() != null) {
            App.setRoot("dashboard");  // Go to the dashboard if login is successful
        } else {
            wrongLogin();
        }
    }

    @FXML
    private void wrongLogin() throws IOException{
    
        login_message.setText("Wrong username or password.");
    }

    @FXML
    private void cancelButton() throws IOException {
        
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void createUser() throws IOException {
        App.setRoot("createAccount");
    }
} 