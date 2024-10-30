package edu.bsu.cs;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class GUI extends Application implements Initializable{

    protected static Stage stage;


    public static ObservableList<Item> itemsForList = FXCollections.observableArrayList();
    EventHandler eventHandler = new EventHandler();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("mainApp.fxml")));
        primaryStage.setTitle("D&D Loot Generator");
        primaryStage.getIcons().add(new Image(("/toolbar_icon.png")));
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
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

    protected static void clearItems(TableView<Item> itemTableView){
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

    protected static void displayMissingFilesAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Missing Files");
        alert.setHeaderText("You are missing necessary data files.\nThis will be the case the first time the program runs.");
        alert.setContentText("Missing data files will be built now.\nThis may take a minute.");
        alert.show();
        showWebView(new EventHandler().webView);
    }
    public static void showWebView(WebView webView) {
        webView.getEngine().load("https://www.youtube.com/embed/s8MDNFaGfT4?autoplay=1");
        Scene scene = new Scene(webView);
        stage.setScene(scene);
        stage.show();
    }

    public static void showMainStage(WebView webView) throws IOException {
        webView.getEngine().load(null);
        Parent root = FXMLLoader.load(Objects.requireNonNull(GUI.class.getClassLoader().getResource("mainApp.fxml")));
        stage.setTitle("D&D Loot Generator");
        stage.getIcons().add(new Image(("/toolbar_icon.png")));
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

}