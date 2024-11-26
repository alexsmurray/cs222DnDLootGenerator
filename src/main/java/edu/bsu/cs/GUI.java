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
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class GUI extends Application implements Initializable{

    protected static Stage stage;
    protected static ObservableList<Item> itemsForList = FXCollections.observableArrayList();
    protected MainScreenController mainScreenController = new MainScreenController();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("MainScreen.fxml")));
        primaryStage.setTitle("D&D Loot Generator");
        primaryStage.getIcons().add(new Image(("/images/toolbarIcon.png")));
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            mainScreenController.initialize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void displayGeneratedItems(TableView<Item> itemTableView, int numberOfItemsToGenerate){
        ItemGenerator itemGenerator = new ItemGenerator();
        clearItems(itemTableView);
        itemsForList.addAll(itemGenerator.generateAmountOfItems(numberOfItemsToGenerate));
        itemTableView.setItems(itemsForList);
    }

    protected static void clearItems(TableView<Item> itemTableView){
        itemsForList.removeAll();
        itemTableView.getItems().clear();
    }

    protected static void displayItemDetails(TableView<Item> itemTableView) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (itemTableView.getSelectionModel().getSelectedItem() != null) {
            alert.setTitle("Item Details");
            alert.setHeaderText("You clicked on " + itemTableView.getSelectionModel().getSelectedItem().getName());
            alert.setContentText(itemTableView.getSelectionModel().getSelectedItem().getDetails());
            itemTableView.getSelectionModel().select(null);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.getDialogPane().setMinWidth(800);
            alert.show();
        }
    }

    protected void displayTableViewLoading(TableView<Item> itemTableView) {
        GUI.clearItems(itemTableView);
        itemTableView.setPlaceholder(new Label("Downloading items from the internet.\nPlease wait until finished."));
    }

    protected void displayTableViewDefault(TableView<Item> itemTableView) {
        itemTableView.setPlaceholder(new Label("Enter above how many items you would like to generate and then press the generate button! :)"));
    }

    protected static void displayLastRefreshDate(Label RefreshDate, String filePath) throws IOException {
        String output = OutputFormatter.formatDateTime(RefreshTracker.readTimeFile(filePath));
        RefreshDate.setText("Last Refresh was " + output);
    }

    public static void displayNoRecentRefresh(Label RefreshDate) {
        RefreshDate.setText("No Recent Refresh");
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
    }

    protected static void displayFileWriteAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Configuration File Write Error");
        alert.setHeaderText("Directory not found.\nUnable to update configuration file.");
        alert.show();
    }

    protected static void displayHomebrewWeaponFieldsAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Missing required inputs");
        alert.setHeaderText("Please fill out the required fields:\n- Name\n- Damage Dice");
        alert.show();
    }

    protected static void displayNoItemsWithCurrentFilters() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Filter Issue");
        alert.setHeaderText("No Items will be displayed with your current filters.");
        alert.show();
    }

    protected static void displayItemCreatedAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Item Created");
        alert.setHeaderText("Your Item has been forged.");
        alert.show();
    }

}