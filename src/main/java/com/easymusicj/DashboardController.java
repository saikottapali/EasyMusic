package com.easymusicj;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class DashboardController {

    @FXML
    private Button btn_logOut;

    @FXML
    private Button btn_playSong;


    @FXML
    private void handleLogOut() throws IOException{

        Stage stage = (Stage) btn_logOut.getScene().getWindow();
        App.setRoot("login");

    }

    @FXML
    private void playSong() throws IOException {
        // Add logic to play a song
        Stage stage = (Stage) btn_playSong.getScene().getWindow();
        App.setRoot("playsong");
    }
}
