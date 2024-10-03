package edu.bsu.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;


public class GUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmlSample.fxml")));
        primaryStage.setTitle("D&D Loot Generator");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}