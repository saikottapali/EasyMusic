package com.easymusicj;

import java.io.IOException;

import com.model.EasyMusicFacade;

import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class DashboardController {

    @FXML
    private Button btn_logOut;

    @FXML
    private Button btn_playSong;

    private EasyMusicFacade facade;

    public DashboardController() {

        facade = EasyMusicFacade.getInstance();
    }

    @FXML
    private void handleLogOut() throws IOException {
        facade.logOut();
        App.setRoot("login");
    }

    @FXML
    private void playSong() throws IOException {
        // Add logic to play a song
        App.setRoot("playsong");
    }
}
