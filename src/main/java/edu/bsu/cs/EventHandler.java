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
        itemTableView.setPlaceholder(new Label("Enter above how many items you would like to generate and then press the generate button! :)"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        rarityTableColumn.setCellValueFactory(new PropertyValueFactory<>("rarity"));
        typeTableColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        attunementTableColumn.setCellValueFactory(new PropertyValueFactory<>("attunement"));
    }

    @FXML
    public void generateItems() throws IOException {
        if (ErrorHandler.verifyInputIsValid(userInputField.getText())) {
            if (!ErrorHandler.verifyAllItemFilesExist()) {
                refreshItemData();
                return;
            }
            int numberOfItemsToGenerate = Integer.parseInt(userInputField.getText());
            Configuration.setNumItemsRequested(numberOfItemsToGenerate);
            GUI.displayGeneratedItems(itemTableView);
        } else {
            GUI.displayInputAlert();
        }
    }

    public void executeOnEnter(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER){
            generateItems();
        }
    }

    public void refreshItemData()  {
        GUI.displayRefreshStarting();
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
                enableInput();
            }
            @Override
            protected void failed(){
                if (ErrorHandler.verifyNetworkConnection().equals("Network Error")) {
                    GUI.displayNetworkAlert();
                } else {
                    GUI.displayRefreshErrorAlert();
                }
                enableInput();
            }
        };
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

}
