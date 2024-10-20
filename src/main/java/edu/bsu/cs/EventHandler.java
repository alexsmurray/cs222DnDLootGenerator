package edu.bsu.cs;

import javafx.concurrent.Task;
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


    public void initialize()  {
        setTableViewToDefault();
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        rarityTableColumn.setCellValueFactory(new PropertyValueFactory<>("rarity"));
        typeTableColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        attunementTableColumn.setCellValueFactory(new PropertyValueFactory<>("attunement"));
    }

    @FXML
    protected void generateItems() throws IOException {
        if (ErrorHandler.verifyInputIsValid(userInputField.getText())) {
            if (!ErrorHandler.verifyItemDataFilesValid()) {
                GUI.displayMissingFilesAlert();
                setTableViewToLoading();
                new Thread(attemptToRefreshItemFiles()).start();
                return;
            }
            int numberOfItemsToGenerate = Integer.parseInt(userInputField.getText());
            Configuration.setNumItemsRequested(numberOfItemsToGenerate);
            GUI.displayGeneratedItems(itemTableView);
        } else {
            GUI.displayInputAlert();
        }
    }

    @FXML
    protected void executeGenerateItemsOnEnter(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER){
            generateItems();
        }
    }


    @FXML
    protected void refreshItemData()  {
        GUI.displayRefreshStarting();
        setTableViewToLoading();
        new Thread(attemptToRefreshItemFiles()).start();
    }

    private Task<Void> attemptToRefreshItemFiles(){
        return new Task<>() {
            @Override
            protected Void call() throws IOException, URISyntaxException {
                disableInput();
                JsonFileMaker.updateAPIFiles();
                return null;
            }
            @Override
            protected void succeeded(){
                GUI.displayRefreshDone();
                setTableViewToDefault();
                enableInput();
            }
            @Override
            protected void failed(){
                if (ErrorHandler.verifyNetworkConnection().equals("Network Error")) {
                    GUI.displayNetworkAlert();
                } else {
                    GUI.displayRefreshErrorAlert();
                }
                setTableViewToDefault();
                enableInput();
            }
        };
    }

    private void setTableViewToLoading() {
        GUI.clearItems(itemTableView);
        itemTableView.setPlaceholder(new Label("Downloading items from the internet.\nPlease wait until finished."));
    }

    private void setTableViewToDefault() {
        itemTableView.setPlaceholder(new Label("Enter above how many items you would like to generate and then press the generate button! :)"));
    }

    private void disableInput() {
        userInputField.setDisable(true);
        generateButton.setDisable(true);
        refreshItemDataButton.setDisable(true);
    }

    private void enableInput(){
        userInputField.setDisable(false);
        generateButton.setDisable(false);
        refreshItemDataButton.setDisable(false);
    }

    @FXML
    protected void displayItemDetailsOnMouseClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (itemTableView.getSelectionModel().getSelectedItem() != null) {
            alert.setTitle("Item Details");
            alert.setHeaderText("You clicked on " + itemTableView.getSelectionModel().getSelectedItem().getName());
            itemTableView.getSelectionModel().select(null);
            alert.show();
        }
    }
}
