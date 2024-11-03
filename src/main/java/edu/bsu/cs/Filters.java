package edu.bsu.cs;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class Filters {

    protected void goBackToMain() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mainApp.fxml")));
        GUI.stage.getScene().setRoot(root);
    }

    protected void goToHomebrew() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/homebrew.fxml")));
        GUI.stage.getScene().setRoot(root);
    }

}