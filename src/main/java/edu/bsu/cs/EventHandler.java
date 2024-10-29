package edu.bsu.cs;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

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
    public Label RefreshDate;
    public Button homebrewButton;

    public WebView webView = new WebView();

    public void initialize()  {
        setTableViewToDefault();
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        rarityTableColumn.setCellValueFactory(new PropertyValueFactory<>("rarity"));
        typeTableColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        attunementTableColumn.setCellValueFactory(new PropertyValueFactory<>("attunement"));

        updateRefreshDate();
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
        GUI.showWebView(webView);
        new Thread(attemptToRefreshItemFiles()).start();
    }



    private Task<Void> attemptToRefreshItemFiles(){
        return new Task<>() {
            @Override
            protected Void call() throws IOException, URISyntaxException {
                disableInput();
                if(ErrorHandler.verifyNetworkConnection().equals("Network Error")){
                    throw new IOException();
                }
                JsonFileMaker.updateAPIFiles();
                return null;
            }
            @Override
            protected void succeeded(){
                GUI.displayRefreshDone();
                RefreshTracker.saveCurrentTime("src/main/resources/lastRefreshDate.txt");
                updateRefreshDate();
                setTableViewToDefault();
                enableInput();
                try {
                    GUI.showMainStage(webView);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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

    private void updateRefreshDate(){
        String filePath = "src/main/resources/lastRefreshDate.txt";
        try{
            displayLastRefreshDate(filePath);
        }catch (Exception IOException){
            RefreshDate.setText("No Recent Refresh");
        }
    }

    @FXML
    protected void displayItemDetailsOnDoubleClick() {
        itemTableView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                displayItemDetails();
            }
        });
    }

    protected void displayItemDetails() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (itemTableView.getSelectionModel().getSelectedItem() != null) {
            alert.setTitle("Item Details");
            alert.setHeaderText("You clicked on " + itemTableView.getSelectionModel().getSelectedItem().getName());
            alert.setContentText(itemTableView.getSelectionModel().getSelectedItem().getDetails());
            itemTableView.getSelectionModel().select(null);
            alert.show();
        }
    }

    protected void displayLastRefreshDate(String filePath) throws IOException {
        String output = OutputFormatter.formatDateTime(RefreshTracker.readTimeFile(filePath));
        RefreshDate.setText("Last Refresh was " + output);
    }

    public void switchToHomeBrew() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/homebrew.fxml")));
        GUI.stage.getScene().setRoot(root);
    }
}
