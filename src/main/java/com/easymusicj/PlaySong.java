package com.easymusicj;

import java.io.IOException;

import org.jfugue.player.Player;

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
    Player player = new Player();
    String notes = getNotesForSong(songName);
    if (notes != null) {
        player.play(notes);
    } else {
        dashboard_message.setText("No notes available for this song.");
    }
}

private String getNotesForSong(String songName) {
    switch (songName) {
        case "Fur Elise":
            return "E D# E D# E B D C A"; // <--- put correct notes here
        case "I Won't Back Down":
            return "G G G A G F G"; // <--- example notes
        case "American Girl":
            return "G G G F E D"; // <--- example notes
        default:
            return null;
    }
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

