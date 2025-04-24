package com.easymusicj;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class PlaySong {

    @FXML
    private Button btn_playSong;

    @FXML
    private Button btn_logout;

    @FXML
    private Label dashboard_message;

    @FXML
    private ListView<String> songListView;

    @FXML 
    public void initialize(){
        ObservableList<String> songs = FXCollections.observableArrayList(
            "I Won't Back Down", "Fur Elise", "American Girl"
        );

        songListView.setItems(songs);

    /**
     * Handles songs selection within scene builder
     **/
    songListView.setOnMouseClicked(event ->{
        String selectedSong = songListView.getSelectionModel().getSelectedItem();
        if (selectedSong != null){
            playSong(selectedSong);
        }   
     });
    }

    private void playSong(String songName){
        System.out.println("Playing: " + songName);
    }

    
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

