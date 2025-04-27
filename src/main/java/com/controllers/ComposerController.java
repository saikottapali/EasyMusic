package com.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jfugue.player.Player;

import com.model.EasyMusicFacade;
import com.model.Note;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class ComposerController {

    @FXML 
    private ComboBox<String> noteBox;
    
    @FXML 
    private ComboBox<String> durationBox;
    
    @FXML 
    private TextArea patternDisplay;

    @FXML
    private Button btn_return;

    @FXML
    private Button btn_save;

    @FXML
    private TextField titleField;

    @FXML
    private ComboBox<String> difficultyBox;

    @FXML
    private CheckBox privateCheckBox;

    @FXML
    private Label composer_label;

    private final StringBuilder pattern = new StringBuilder();

    private EasyMusicFacade facade;

    public ComposerController() {
        facade = EasyMusicFacade.getInstance();
    }

    @FXML
    private void initialize() {
        noteBox.getItems().addAll("C5", "C#5", "D5", "D#5", "E5", "E#5","F5", 
        "F#5", "G5", "G#5", "A5", "A#5", "B5", "B#5");
        noteBox.setValue("C5");
        difficultyBox.getItems().addAll("EASY", "MEDIUM", "HARD");
        durationBox.getItems().addAll("w", "h", "q", "i", "s");
        durationBox.setValue("q");    
    }
    @FXML
    private void handleAdd() {
        String note = noteBox.getValue();
        String duration = durationBox.getValue();
        
        if (note == null || duration == null) {
            composer_label.setText("Please select a note or duration.");
            return;
        }

        patternDisplay.appendText(note + duration + " ");
    }
    
    @FXML
    private void handlePlay() {
        
        if (pattern.length() == 0) return;

        Player player = new Player();
        player.play(pattern.toString().trim());
        
    }
    @FXML
    private void handleClear() {
        pattern.setLength(0);
        patternDisplay.clear();
    }

    @FXML
    private void handleReturn() throws IOException{
        App.setRoot("dashboard");
    }

    @FXML
    private void handleSave() {
        String title = titleField.getText();
        String difficulty = difficultyBox.getValue();
        boolean isPrivate = privateCheckBox.isSelected();

        String[] tokens = patternDisplay.getText().trim().split("\\s+");
        List<Note> notes = new ArrayList<>(); // <-- Create a list to hold all notes

        for (String token : tokens) {
            if (token.length() < 2) continue; // Safety check for weird input

            String notePart = token.substring(0, token.length() - 1); // "C#5" or "D5"
            String durationPart = token.substring(token.length() - 1); // "q", "h", etc.

            // Handle name and octave correctly
            String name = notePart.length() == 3 ? notePart.substring(0, 2) : notePart.substring(0, 1); // "C" or "C#"
            int octave = Character.getNumericValue(notePart.charAt(notePart.length() - 1)); // last char is octave
            String pitch = notePart; // Full pitch (e.g., "C#5")

            Note note = new Note(pitch, name, octave, durationPart);
            notes.add(note); // Add to list!
        }

        if (notes.isEmpty()) {
            composer_label.setText("Please add notes to the song.");
            return;
        }
        if (title.isEmpty()) {
            composer_label.setText("Please enter a title.");
            return;
        }
        if (difficulty == null) {
            composer_label.setText("Please select a difficulty level.");
            return;
        }

        if (title.isEmpty() || difficulty == null) {
            composer_label.setText("Please fill in all fields.");
            return;
        }

        if (facade.createSong(title, difficulty, isPrivate, notes)) {
            composer_label.setText("Song saved successfully!");
        } else {
            composer_label.setText("Failed to save song.");
        }
    }

}