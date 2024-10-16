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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class GUI extends Application implements Initializable {




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
    public TextField userInputField;
    public ListView<String> generatedList;
    public Button generateButton;
    public ObservableList<String> itemsForList = FXCollections.observableArrayList();

    @FXML
    public void generateItems(ActionEvent ignoredEvent) throws IOException {
        int numberOfItemsToGenerate = Integer.parseInt(userInputField.getText());
        Configuration.setNumItemsRequested(numberOfItemsToGenerate);
        ItemBuilder itemBuilder = new ItemBuilder();
        generatedList.setItems(itemsForList);
        for (Item item : itemBuilder.generateAmountOfItems()) {
            String formattedLine = OutputFormatter.formatItemForList(item);
            itemsForList.add(formattedLine);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generatedList.getItems().addAll(itemsForList);
    }
}