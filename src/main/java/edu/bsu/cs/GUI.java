package edu.bsu.cs;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class GUI extends Application implements Initializable {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("mainApp.fxml")));
            primaryStage.setTitle("D&D Loot Generator");
            primaryStage.setScene(new Scene(root));
            String networkCheck = checkForFileUpdate();
            primaryStage.show();
            displayNetworkAlert(networkCheck);
    }

    @FXML
    public TextField userInputField;
    public TableView<Item> itemTableView;
    public TableColumn<Item, String> nameTableColumn;
    public TableColumn<Item, String> rarityTableColumn;
    public TableColumn<Item, String> typeTableColumn;
    public TableColumn<Item, String> attunementTableColumn;
    public Button generateButton;
    public ObservableList<Item> itemsForList = FXCollections.observableArrayList();

    @FXML
    public void generateItems(ActionEvent ignoredEvent) throws IOException {
        if (ErrorHandler.verifyInputIsValid(userInputField.getText())) {
            int numberOfItemsToGenerate = Integer.parseInt(userInputField.getText());
            Configuration.setNumItemsRequested(numberOfItemsToGenerate);
            ItemBuilder itemBuilder = new ItemBuilder();
            itemsForList.removeAll();
            itemTableView.getItems().clear();
            itemsForList.addAll(itemBuilder.generateAmountOfItems());
            itemTableView.setItems(itemsForList);
        } else {
            displayInputAlert();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        itemTableView.setPlaceholder(new Label("Enter above how many items you would like to generate and then press the generate button! :)"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        rarityTableColumn.setCellValueFactory(new PropertyValueFactory<>("rarity"));
        typeTableColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        attunementTableColumn.setCellValueFactory(new PropertyValueFactory<>("attunement"));
    }

    //Change name as you see fit, best I could think of at the time
    public String checkForFileUpdate(){
        Controller controller = new Controller();
        if (!ErrorHandler.verifyNetworkConnection().equals("Network Error")){
            try {
                controller.updateAPIFiles();
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
            return "";
        }else {
            return "Network Error";
        }
    }

    public void displayInputAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input is not an integer");
        alert.setHeaderText("Please enter an integer in the text field.");
        alert.show();
    }

    public static void displayNetworkAlert(String check) {
        if (!check.isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Network Error");
            alert.setHeaderText("Could connect to server.\nUnable to update files.");
            alert.show();
        }
    }

}