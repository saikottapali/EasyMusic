package com.controllers;

import java.io.IOException;

import org.jfugue.player.Player;

import com.model.EasyMusicFacade;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
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

    private final StringBuilder pattern = new StringBuilder();

    private EasyMusicFacade facade;

    public ComposerController() {
        facade = EasyMusicFacade.getInstance();
    }

    @FXML
    private void initialize() {
        noteBox.getItems().addAll("C5", "D5", "E5", "F5", "G5", "A5", "B5");
        noteBox.setValue("C5");
        difficultyBox.getItems().addAll("EASY", "MEDIUM", "HARD");
        durationBox.getItems().addAll("w", "h", "q", "i", "s");
        durationBox.setValue("q");
    }

    @FXML
    private void handleAdd() {
        String note = noteBox.getValue();
        String dur = durationBox.getValue();
        pattern.append(note).append(dur).append(" ");
        patternDisplay.setText(pattern.toString());
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
        String notesInput = pattern.toString().trim();

        if (facade.createSong(title, difficulty, isPrivate, notesInput)) {
            System.out.println("Song saved successfully!");
        } else {
            System.out.println("Failed to save song.");
        }
    }

}
