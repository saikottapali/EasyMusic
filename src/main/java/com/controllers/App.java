package com.controllers;

import java.io.IOException;

import com.model.EasyMusicFacade;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    private EasyMusicFacade facade;

    public App() {
        facade = EasyMusicFacade.getInstance();
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 600, 400);
        stage.setScene(scene);
        stage.setTitle("EasyMusic");

        stage.setOnCloseRequest(event -> {
            // Check if there is a logged-in user
            if (facade.getCurrentUser() != null) {
                facade.logOut();  // Log out the user when closing the application
            }
            Platform.exit();  // Close the application
        });
        stage.show();
    }
    
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}