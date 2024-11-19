package edu.bsu.cs;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class MainScreenController {

    @FXML
    public TextField userInputField;
    public TableView<Item> itemTableView;
    public TableColumn<Item, String> nameTableColumn;
    public TableColumn<Item, String> rarityTableColumn;
    public TableColumn<Item, String> typeTableColumn;
    public TableColumn<Item, String> attunementTableColumn;
    public Button generateButton;
    public Button refreshItemDataButton;
    public Label refreshDate;
    public MenuButton navigationMenu;
    public ImageView loadingImage;

    private AudioClip audioClip;
    private RotateTransition rotateAnimation;


    public void initialize() {
        initializeTableView();
        setConfigurationToDefault();
        updateRefreshDate();
    }

    private void initializeTableView() {
        new GUI().displayTableViewDefault(itemTableView);
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        rarityTableColumn.setCellValueFactory(new PropertyValueFactory<>("rarity"));
        typeTableColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        attunementTableColumn.setCellValueFactory(new PropertyValueFactory<>("attunement"));
    }

    private void setConfigurationToDefault() {
        try {
            new ConfigurationFileWriter().initializeConfigFile(new ConfigurationTable());
        } catch (Exception ConfigException) {
            //TODO::file write error handling needed
        }
    }

    private void updateRefreshDate(){
        String filePath = "src/main/resources/lastRefreshDate.txt";
        try{
            GUI.displayLastRefreshDate(refreshDate, filePath);
        }catch (Exception IOException){
            GUI.displayNoRecentRefresh(refreshDate);
        }
    }

    @FXML
    protected void executeGenerateItemsOnEnter(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER){
            generateItems();
        }
    }

    @FXML
    protected void generateItems() throws IOException {
        if (ErrorHandler.verifyInputIsValid(userInputField.getText())) {
            attemptToPopulateItemTableView();
        } else {
            GUI.displayInputAlert();
        }
    }

    private void attemptToPopulateItemTableView() throws IOException {
        if (!ErrorHandler.verifyItemDataFilesValid()) {
            GUI.displayMissingFilesAlert();
            initiateLoadingProcess();
        } else {
            new Thread(populateTableView()).start();
        }
    }

    private Task<Void> populateTableView() {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {
                disableInput();
                int numberOfItemsToGenerate = Integer.parseInt(userInputField.getText());
                GUI.displayGeneratedItems(itemTableView, numberOfItemsToGenerate);
                return null;
            }
            @Override
            protected void succeeded() {
                enableInput();
            }
            @Override
            protected void failed(){
                enableInput();
            }
        };
    }

    @FXML
    protected void refreshItemData() {
        GUI.displayRefreshStarting();
        initiateLoadingProcess();
    }

    private void initiateLoadingProcess() {
        new GUI().displayTableViewLoading(itemTableView);
        playAudioClip();
        animateLoadingImage();
        new Thread(attemptToRefreshItemFiles()).start();
    }

    private void playAudioClip() {
        String fileName;
        try {
            fileName = Objects.requireNonNull(getClass().getResource("/audio/funTune.mp3")).toURI().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        audioClip = new AudioClip(fileName);
        audioClip.play();
    }

    private void animateLoadingImage() {
        loadingImage.setVisible(true);
        rotateAnimation = new RotateTransition(Duration.seconds(5), loadingImage);
        rotateAnimation.setFromAngle(0);
        rotateAnimation.setToAngle(-360);
        rotateAnimation.setCycleCount(Timeline.INDEFINITE);
        rotateAnimation.setInterpolator(Interpolator.LINEAR);
        rotateAnimation.play();
    }

    private void stopLoadingImage() {
        loadingImage.setVisible(false);
        rotateAnimation.stop();
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
                RefreshTracker.saveCurrentTime("src/main/resources/dataFiles/lastRefreshDate.txt");
                updateRefreshDate();
                new GUI().displayTableViewDefault(itemTableView);
                audioClip.stop();
                stopLoadingImage();
                enableInput();
            }
            @Override
            protected void failed(){
                if (ErrorHandler.verifyNetworkConnection().equals("Network Error")) {
                    GUI.displayNetworkAlert();
                } else {
                    GUI.displayRefreshErrorAlert();
                }
                audioClip.stop();
                stopLoadingImage();
                new GUI().displayTableViewDefault(itemTableView);
                enableInput();
            }
        };
    }

    private void disableInput() {
        userInputField.setDisable(true);
        generateButton.setDisable(true);
        refreshItemDataButton.setDisable(true);
        navigationMenu.setDisable(true);
    }

    private void enableInput(){
        userInputField.setDisable(false);
        generateButton.setDisable(false);
        refreshItemDataButton.setDisable(false);
        navigationMenu.setDisable(false);
    }

    @FXML
    protected void displayItemDetailsOnDoubleClick() {
        itemTableView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                GUI.displayItemDetails(itemTableView);
            }
        });
    }

    public void switchToHomeBrew() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/HomebrewScreen.fxml")));
        GUI.stage.getScene().setRoot(root);
    }
    public void switchToFilters() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FilterScreen.fxml")));
        GUI.stage.getScene().setRoot(root);
    }

}
