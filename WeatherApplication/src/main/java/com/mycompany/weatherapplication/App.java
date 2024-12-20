package com.mycompany.weatherapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // Load the initial FXML layout
        scene = new Scene(loadFXML("View"));
        
        // Set the scene to the stage
        stage.setScene(scene);
        
        // Set the application icon
        Image icon = new Image(getClass().getResourceAsStream("img/icon1.png"));
        stage.getIcons().add(icon);
        
        // Set the title of the application window
        stage.setTitle("EELU Weather");
        
        // Show the application window
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