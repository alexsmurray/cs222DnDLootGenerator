package edu.bsu.cs;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class GUI extends Application implements Initializable{

    public static ObservableList<Item> itemsForList = FXCollections.observableArrayList();
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

    public static void displayGeneratedItems(TableView<Item> itemTableView) throws IOException {
        ItemBuilder itemBuilder = new ItemBuilder();
        clearItems(itemTableView);
        itemsForList.addAll(itemBuilder.generateAmountOfItems());
        itemTableView.setItems(itemsForList);
    }

    private static void clearItems(TableView<Item> itemTableView){
        itemsForList.removeAll();
        itemTableView.getItems().clear();
    }

    protected static void displayInputAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input is not valid");
        alert.setHeaderText("Please enter an integer in the range of 1 to 100.");
        alert.show();
    }

    protected static void displayNetworkAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Network Error");
        alert.setHeaderText("Couldn't connect to server.\nUnable to update files.");
        alert.show();
    }

    protected static void displayRefreshStarting() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Refresh In Progress");
        alert.setHeaderText("We're updating your data from the server.\nThis may take a minute.");
        alert.show();
    }

    protected static void displayRefreshDone() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Refresh Complete");
        alert.setHeaderText("Your files are up to date.");
        alert.show();
    }

    protected static void displayRefreshErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Refresh Error");
        alert.setHeaderText("Your files could not be updated.");
        alert.show();
    }

}