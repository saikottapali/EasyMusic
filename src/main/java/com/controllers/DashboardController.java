package com.controllers;

import java.io.IOException;

import com.model.EasyMusicFacade;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class DashboardController {

    @FXML
    private Button btn_logOut;

    @FXML
    private Button btn_playSong;

    @FXML
    private Button btn_composeSong;

    @FXML
    private Label welcome_label;

    private EasyMusicFacade facade;

    public DashboardController() {

        facade = EasyMusicFacade.getInstance();
    }

    public void initialize() {

        String username = facade.getCurrentUser().getUsername();
        welcome_label.setText("Welcome " + username + "!");
    }

    @FXML
    private void handleLogOut() throws IOException {
        facade.logOut();
        App.setRoot("login");
    }

    @FXML
    private void playSong() throws IOException {
        App.setRoot("playsong");
    }

    @FXML
    private void composeSong() throws IOException {
        App.setRoot("composer");
    }
}
