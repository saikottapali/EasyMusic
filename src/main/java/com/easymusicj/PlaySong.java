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

        dashboard_message.setText("Select a song");

        btn_playSong.setDisable(true);

         /**
          * Listens for selection change
          */
        songListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        btn_playSong.setDisable(newSelection == null);
    });

    /**
     * Handles songs selection within scene builder
     **/
        songListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            btn_playSong.setDisable(false);
            dashboard_message.setText("Selected: " + newSelection);
        } else {
            btn_playSong.setDisable(true);
            dashboard_message.setText("Select a song");
        }
    });
    }

    private void playSong(String songName) {
        Player player = new Player();
        String notes = getNotesForSong(songName);
        if (notes != null) {
            player.play("T150 " + notes);
        } else {
            dashboard_message.setText("No notes available for this song.");
        }
    }

    private String getNotesForSong(String songName) {
        switch (songName) {
            case "Fur Elise":
                return "E5q D#5q E5q D#5q E5q B4q D5q C5q A4q";
            case "I Won't Back Down":
                return "G4q Rq G4q Rq G4q A4q G4q F4q G4q";
            case "American Girl":
                return "G4q G4q G4q F4q E4q D4q G4q G4q G4q F4q E4q D4q G4q G4q G4q A4q G4q F4q G4q";
            default:
                return null;
        }
    }    

    /** 
     * @throws IOException
     */
    @FXML
private void playSong() throws IOException {
    String selectedSong = songListView.getSelectionModel().getSelectedItem();
    if (selectedSong != null) {
        playSong(selectedSong); 
        dashboard_message.setText("Select another song" + ""+"🎶");
    } else {
        dashboard_message.setText("Please select a song first!");
    }
}

   @FXML
private void back() throws IOException {
    App.setRoot("dashboard"); 
}

}

