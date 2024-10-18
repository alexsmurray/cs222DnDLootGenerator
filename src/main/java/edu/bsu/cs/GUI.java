package edu.bsu.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class GUI extends Application implements Initializable{
    EventHandler eventHandler = new EventHandler();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("mainApp.fxml")));
            primaryStage.setTitle("D&D Loot Generator");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        eventHandler.initialize();
    }

    public static void displayInputAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input is not an integer");
        alert.setHeaderText("Please enter an integer in the text field.");
        alert.show();
    }

    public static void displayNetworkAlert(String check) {
        if (!check.isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Network Error");
            alert.setHeaderText("Couldn't connect to server.\nUnable to update files.");
            alert.show();
        }
    }

    public static void displayRefreshStarting() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Refresh In Progress");
        alert.setHeaderText("We're updating your data from the server.\nThis may take a minute.");
        alert.show();
    }

    public static void displayRefreshDone() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Refresh Complete");
        alert.setHeaderText("Your files are up to date.");
        alert.show();
    }
}