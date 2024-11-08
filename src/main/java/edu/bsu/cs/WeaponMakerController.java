package edu.bsu.cs;

import javafx.scene.control.*;
import javafx.scene.layout.Pane;


public class WeaponMakerController {
    public RadioButton simpleRadio;
    public RadioButton martialRadio;
    public ChoiceBox<String> damageDiceChoice;
    public ChoiceBox<String> reachChoice;
    public ChoiceBox<String> weaponRarityChoice;
    public ChoiceBox<String> damageTypeChoice;
    public Pane weaponMaker;
    public TextField weaponNameInput;
    public TextField numberOfDiceInput;
    public TextArea weaponDescription;
    public ToggleButton attunementToggle;
    public TextField weaponRangeInput;
    public Label requiresAttunementLabel;
    public CheckBox thrownCheckBox;
    public CheckBox ammunitionCheckBox;
    public Label longRangeLabel;
    public ToggleGroup proficiency;


    public void setLongRangeLabel() {
        if (thrownCheckBox.isSelected()) {
            longRangeLabel.setText("Long Range: " + Integer.parseInt(weaponRangeInput.getText()) * 3);
            ammunitionCheckBox.setDisable(true);
        } else if (ammunitionCheckBox.isSelected()) {
            longRangeLabel.setText("Long Range: " + Integer.parseInt(weaponRangeInput.getText()) * 4);
            thrownCheckBox.setDisable(true);
        }
        else {
            ammunitionCheckBox.setDisable(false);
            thrownCheckBox.setDisable(false);
        }
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

    public void setRangeSettings() {
        String input = weaponRangeInput.getText();
        if (!input.isBlank() && checkForNumber(input)) {
            ammunitionCheckBox.setDisable(false);
            thrownCheckBox.setDisable(false);
        } else {
            ammunitionCheckBox.setDisable(true);
            thrownCheckBox.setDisable(true);
        }
    }

    public void checkNumberOfDiceInput() {
        if (!checkForNumber(numberOfDiceInput.getText())) {
            numberOfDiceInput.clear();
        }
    }

    protected boolean checkForNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception exception) {
            displayRangeInputAlert();
            return false;
        }
    }

    protected static void displayRangeInputAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input is not valid");
        alert.setHeaderText("Please enter an whole number.");
        alert.show();
    }
}
