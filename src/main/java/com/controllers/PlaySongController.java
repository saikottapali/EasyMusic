package com.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.jfugue.player.Player;

import com.model.EasyMusicFacade;
import com.model.Song;
import com.model.Note;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class PlaySongController {

    @FXML
    private Button btn_playSong;

    @FXML
    private Button btn_logout;

    @FXML
    private Label dashboard_message;

    @FXML
    private ListView<String> songListView;

    private EasyMusicFacade facade;

    public PlaySongController() {
        facade = EasyMusicFacade.getInstance();
    }

    @FXML 
    public void initialize() {
        // Load songs from MusicLibrary via the Facade
        List<Song> allSongs = facade.getMusicLibrary().getSongs();  // Assumes getSongs() gives you all songs
        List<Song> publicSongs = allSongs.stream()
                                         .filter(song -> !song.isPrivate())  // Exclude private songs
                                         .collect(Collectors.toList());

        // Convert list of songs into ObservableList for ListView
        ObservableList<String> songTitles = FXCollections.observableArrayList();
        for (Song song : publicSongs) {
            songTitles.add(song.getTitle());  // Add song title to ListView
        }

        songListView.setItems(songTitles);

        dashboard_message.setText("Select a song");

        btn_playSong.setDisable(true);

        // Listens for selection change
        songListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            btn_playSong.setDisable(newSelection == null);
        });
    }

    @FXML
    private void playSong() throws IOException {
        String selectedSongTitle = songListView.getSelectionModel().getSelectedItem();
        if (selectedSongTitle != null) {
            // Get the full Song object from the selected title
            Song selectedSong = facade.getMusicLibrary().getSongs().stream()
                                    .filter(song -> song.getTitle().equals(selectedSongTitle))
                                    .findFirst()
                                    .orElse(null);

            if (selectedSong != null) {
                String notes = getNotesForSong(selectedSong);
                if (notes != null) {
                    // Play the song with notes using JFugue player
                    Player player = new Player();
                    player.play("T150 " + notes);  // Use tempo and notes string
                    dashboard_message.setText("Playing: " + selectedSongTitle + " 🎶");
                } else {
                    dashboard_message.setText("No notes available for this song.");
                }
            }
        } else {
            dashboard_message.setText("Please select a song first!");
        }
    }

    // Fetch the notes for the selected song
    private String getNotesForSong(Song song) {
        StringBuilder notes = new StringBuilder();
        for (Note note : song.getSongNotes()) {
            // Convert note into a string format that JFugue understands
            notes.append(note.getPitch()).append(note.getDuration()).append(" ");
        }
        return notes.toString().trim();
    }

    @FXML
    private void back() throws IOException {
        App.setRoot("dashboard");  // Go back to the dashboard
    }
}
