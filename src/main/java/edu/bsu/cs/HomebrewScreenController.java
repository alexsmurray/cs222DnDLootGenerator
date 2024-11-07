package edu.bsu.cs;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomebrewScreenController {

    public Pane weaponMaker;
    public TextField weaponNameInput;
    public RadioButton simpleRadio;
    public RadioButton martialRadio;
    public ChoiceBox damageDiceChoice;
    public ListView propertyListView;
    public MenuButton propertyMenu;
    public TextField numberOfDiceInput;
    public TextArea weaponDescription;
    public ChoiceBox reachChoice;
    public ChoiceBox weaponRarityChoice;
    public ToggleButton attunementToggle;
    public ChoiceBox extraTypeChoice;
    public ChoiceBox damageTypeChoice;
    public TextField weaponRangeInput;


    public void goBackToMain() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/MainScreen.fxml")));
        GUI.stage.getScene().setRoot(root);
    }

    public void goToFilters() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FilterScreen.fxml")));
        GUI.stage.getScene().setRoot(root);
    }

    public String getWeaponName() {
        return weaponNameInput.getText();
    }

    public void checkAttunement() {
//        attunementToggle.setOnAction(event -> {
//            if (attunementToggle.isSelected()) {
//                attunementToggle.setStyle("-fx-background-color: lightgreen");
//            } else {
//                attunementToggle.setStyle("-fx-background-color: lightgrey");
//            }
//
//        });
    }

    public boolean getWeaponAttunement() {
        return attunementToggle.isSelected();
    }

    public String getNumberOfDamageDice() {
        return numberOfDiceInput.getText();
    }

    public String getWeaponRange() {
        return weaponRangeInput.getText();
    }

    public String getWeaponDescription() {
        return weaponDescription.getText();
    }

}
