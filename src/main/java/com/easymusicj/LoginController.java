package com.easymusicj;

import java.io.IOException;
import java.util.List;

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
    private TextField txt_username;

    @FXML
    private PasswordField txt_password;

    @FXML
    private Label login_message;


    @FXML
    private void userLogin() throws IOException {
    
        checkLogin();

    }

    private void checkLogin() throws IOException {

        String usernameInput = txt_username.getText();
        String passwordInput = txt_password.getText();

        if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
            login_message.setText("Please enter username and password.");
            return;
        }

        EasyMusicFacade facade =  new EasyMusicFacade();
        facade.login(usernameInput, passwordInput);
        
        if (facade.login(usernameInput, passwordInput) != null) {
            App.setRoot("dashboard");
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
}