package edu.bsu.cs;

import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public Label longRangeLabel;
    public ToggleGroup proficiency;
    public HomebrewScreenController homebrewScreenController = new HomebrewScreenController();
    public CheckBox thrownCheckBox;
    public CheckBox ammunitionCheckBox;
    public CheckBox versatileCheckBox;
    public CheckBox twoHandedCheckBox;
    public CheckBox specialCheckBox;
    public CheckBox reachCheckBox;
    public CheckBox netCheckBox;
    public CheckBox loadingCheckBox;
    public CheckBox lightCheckBox;
    public CheckBox lanceCheckBox;
    public CheckBox heavyCheckBox;
    public CheckBox finesseCheckBox;

    public List<String> getProperties() {
        List<String> propertyNamesList = new ArrayList<>();
        CheckBox[] properties = {thrownCheckBox,ammunitionCheckBox,twoHandedCheckBox,versatileCheckBox,specialCheckBox,reachCheckBox,netCheckBox,loadingCheckBox,lightCheckBox,lanceCheckBox,heavyCheckBox,finesseCheckBox};
        for (CheckBox property : properties) {
            if (property.isSelected()) {
                propertyNamesList.add(property.getText());
            }
        }
        return propertyNamesList;
    }

    protected String getProficiency(){
        if (simpleRadio.isSelected()){
            return "Simple";
        }else {
            return "Martial";
        }
    }

    public void checkAttunement() {
        requiresAttunementLabel.setVisible(attunementToggle.isSelected());
    }

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

    public void setRangeSettings() {
        String input = weaponRangeInput.getText();
        if (!input.isBlank() && homebrewScreenController.checkForNumber(input)) {
            ammunitionCheckBox.setDisable(false);
            thrownCheckBox.setDisable(false);
        } else {
            ammunitionCheckBox.setDisable(true);
            thrownCheckBox.setDisable(true);
            weaponRangeInput.clear();
        }
    }

    public List<String> getRangeProperty(List<String> properties) {
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).contains("*")) {
                properties.set(i, properties.get(i).substring(1) + " (range " + weaponRangeInput.getText() + "/" + longRangeLabel.getText().substring(12) + ")");
            }
        }
        return properties;
    }

    public void checkNumberOfDiceInput() {
        if (!homebrewScreenController.checkForNumber(numberOfDiceInput.getText())) {
            numberOfDiceInput.clear();
        }
    }

    public String collectWeaponDetails(){
        String[] checkedInputs = {weaponNameInput.getText(), numberOfDiceInput.getText()};
        if (!ErrorHandler.verifyHomebrewInputsNotBlank(checkedInputs)) {
            GUI.displayHomebrewWeaponFieldsAlert();
            return "";
        }
        return "{\n" +
                "\t\"Item_Type\": \"Weapon HB\",\n" +
                "\t\"Name\": \"" + weaponNameInput.getText() + "\",\n" +
                "\t\"Rarity\": \"" + weaponRarityChoice.getValue() + "\",\n" +
                "\t\"Attunement\": " + attunementToggle.isSelected() + ",\n" +
                "\t\"Description\":" +
                "\n\t\t\"Proficiency:  " + getProficiency() + "\n" +
                "\t\tDamage Dice:  " + numberOfDiceInput.getText() + damageDiceChoice.getValue() + "\n" +
                "\t\tReach:  " + reachChoice.getValue() + "\n" +
                "\t\tDamage Type:  " + damageTypeChoice.getValue() + "\n" +
                "\t\tProperties:  " + OutputFormatter.formatProperties(getRangeProperty(getProperties())) + "\n\n" +
                "\t\tDescription: " +
                "\n\n\t\t" + weaponDescription.getText() + "\"\n" +
                "},]\n" +
                "}";
    }

    public void writeWeaponToFile() throws IOException {
       String itemDetails = collectWeaponDetails();
       if (!itemDetails.isEmpty()) {
            new HomebrewFileMaker().writeHomebrewToFile(itemDetails);
           GUI.displayItemCreatedAlert();
       }
       clearAllInput();
    }

    public void clearAllInput() {
        TextField[] textFields = {weaponNameInput, weaponRangeInput, numberOfDiceInput};
        for (TextField field: textFields) {
            field.setText("");
        }
        CheckBox[] properties = {thrownCheckBox,ammunitionCheckBox,twoHandedCheckBox,versatileCheckBox,specialCheckBox,reachCheckBox,netCheckBox,loadingCheckBox,lightCheckBox,lanceCheckBox,heavyCheckBox,finesseCheckBox};
        for (CheckBox property: properties) {
            property.setSelected(false);
        }
        thrownCheckBox.setDisable(true);
        ammunitionCheckBox.setDisable(true);
        weaponDescription.setText("");
        simpleRadio.setSelected(true);
        weaponRarityChoice.getSelectionModel().select(1);
        reachChoice.getSelectionModel().select(1);
        damageTypeChoice.getSelectionModel().select(2);
        damageDiceChoice.getSelectionModel().select(1);
        longRangeLabel.setText("Long Range: ");
        attunementToggle.setSelected(false);
        requiresAttunementLabel.setVisible(false);

    }


}
