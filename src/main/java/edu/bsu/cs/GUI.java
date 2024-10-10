package edu.bsu.cs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;


public class GUI extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmlSample.fxml")));
            primaryStage.setTitle("D&D Loot Generator");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
    }

    @FXML
    public TextArea textArea;
    public Button button;

    @FXML
    public void setText(ActionEvent actionEvent) throws URISyntaxException, IOException {
        textArea.setText("Hello");
        /*Weapon weapon = new Weapon();
        textArea.setText(weapon.getName("v2/weapons/"));*/
    }

}