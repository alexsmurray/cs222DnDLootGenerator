package edu.bsu.cs;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.util.Objects;


public class HomebrewScreenController {

    public Pane weaponMaker;
    public TextField weaponNameInput;
    public RadioButton simpleRadio;
    public RadioButton martialRadio;
    public ChoiceBox<String> damageDiceChoice;
    public TextField numberOfDiceInput;
    public TextArea weaponDescription;
    public ChoiceBox<String> reachChoice;
    public ChoiceBox<String> weaponRarityChoice;
    public ToggleButton attunementToggle;
    public ChoiceBox<String> damageTypeChoice;
    public TextField weaponRangeInput;
    public Label requiresAttunementLabel;
    public ToggleGroup proficiency;
    public MenuItem Ammunition;
    public CheckBox thrownCheckBox;
    public CheckBox ammunitionCheckBox;


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
        requiresAttunementLabel.setVisible(attunementToggle.isSelected());
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
    
    public void setAmmunitionAndThrown(){
        if (!weaponRangeInput.getText().isBlank() && !weaponRangeInput.getText().contains(".*[A-Z].*")){
            ammunitionCheckBox.setDisable(false);
            thrownCheckBox.setDisable(false);
        }else {
            ammunitionCheckBox.setDisable(true);
            thrownCheckBox.setDisable(true);
        }
    }
    
}
