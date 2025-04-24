package com.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PlaySong {

    @FXML
    private Button btn_playSong;

    @FXML
    private Button btn_logout;

    @FXML
    private Label dashboard_message;

    
    /** 
     * @throws IOException
     */
    @FXML
    private void playSong() throws IOException {
        // Your logic to play a song
        dashboard_message.setText("Now playing!");
    }

    @FXML
    private void logout() throws IOException {
        // Logic to return to login or main menu
        App.setRoot("login");
    }
}

