package edu.bsu.cs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class EventHandler {

    @FXML
    public TextField userInputField;
    public TableView<Item> itemTableView;
    public TableColumn<Item, String> nameTableColumn;
    public TableColumn<Item, String> rarityTableColumn;
    public TableColumn<Item, String> typeTableColumn;
    public TableColumn<Item, String> attunementTableColumn;
    public Button generateButton;
    public Button refreshItemDataButton;
    public ObservableList<Item> itemsForList = FXCollections.observableArrayList();


    public void initialize()  {
        itemTableView.setPlaceholder(new Label("Enter above how many items you would like to generate and then press the generate button! :)"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        rarityTableColumn.setCellValueFactory(new PropertyValueFactory<>("rarity"));
        typeTableColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        attunementTableColumn.setCellValueFactory(new PropertyValueFactory<>("attunement"));
    }

    @FXML
    public void generateItems() throws IOException {
        if (ErrorHandler.verifyInputIsValid(userInputField.getText())) {
            int numberOfItemsToGenerate = Integer.parseInt(userInputField.getText());
            Configuration.setNumItemsRequested(numberOfItemsToGenerate);
            ItemBuilder itemBuilder = new ItemBuilder();
            clearItems();
            itemsForList.addAll(itemBuilder.generateAmountOfItems());
            itemTableView.setItems(itemsForList);
        } else {
            GUI.displayInputAlert();

        }
    }

    private void clearItems(){
        itemsForList.removeAll();
        itemTableView.getItems().clear();
    }

    public void executeOnEnter(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER){
            generateItems();
        }
    }

    public void refreshItemData()  {
        GUI.displayRefreshStarting();
        String networkCheck = JsonFileMaker.checkForFileUpdate();
        GUI.displayNetworkAlert(networkCheck);
        GUI.displayRefreshDone();
    }

    protected void updateAPIFiles() throws IOException, URISyntaxException {
        JsonFileMaker jsonFileMaker = new JsonFileMaker();
        boolean updated = false;
        while (!updated) {
            jsonFileMaker.writeItemsJsonToFile("magicitems");
            jsonFileMaker.writeItemsJsonToFile("armor");
            jsonFileMaker.writeItemsJsonToFile("weapons");
            updated = true;
        }
    }

}
