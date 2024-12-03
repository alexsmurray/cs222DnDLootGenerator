package edu.bsu.cs;

import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ArmorMakerController {
    public TextField armorNameInput;
    public Pane armorMaker;
    public ChoiceBox<String> dexModChoice;
    public TextArea armorDescription;
    public ChoiceBox<String> armorRarityChoice;
    public ChoiceBox<String> categoryChoice;
    public ToggleButton attunementToggle;
    public Label requiresAttunementLabel;
    public Label stealthDisadvantageLabel;
    public ToggleButton stealthDisadvantageToggle;
    public HomebrewScreenController homebrewScreenController = new HomebrewScreenController();
    public TextField armorClassInput;
    public TextField strengthRequirementInput;

    public void checkAttunement() {
        requiresAttunementLabel.setVisible(attunementToggle.isSelected());
    }
    public void checkStealthDisadvantage() {
        stealthDisadvantageLabel.setVisible(stealthDisadvantageToggle.isSelected());
    }
    public void checkArmorClassInput() {
        if (!homebrewScreenController.checkForNumber(armorClassInput.getText())) {
            armorClassInput.clear();
        }
    }
    public void checkStrengthRequirementInput() {
        if (!homebrewScreenController.checkForNumber(strengthRequirementInput.getText())) {
            strengthRequirementInput.clear();
        }
    }

    public String collectArmorDetails(){
        String[] checkedInputs = {armorNameInput.getText(), armorClassInput.getText(), strengthRequirementInput.getText()};
        if (!ErrorHandler.verifyHomebrewInputsNotBlank(checkedInputs)) {
            GUI.displayHomebrewWeaponFieldsAlert();
            return "";
        }
        return "{\n" +
                "\t\"Item_Type\": \"Armor HB\",\n" +
                "\t\"Name\": \"" + armorNameInput.getText() + "\",\n" +
                "\t\"Rarity\": \"" + armorRarityChoice.getValue() + "\",\n" +
                "\t\"Attunement\": " + attunementToggle.isSelected() + ",\n" +
                "\t\"Description\":" +
                "\n\"AC:  " + armorClassInput.getText() + OutputFormatter.formatDexModifier(dexModChoice.getValue()) + "\n" +
                "Category:  " + categoryChoice.getValue() + "\n" +
                "Strength Requirement:  " + strengthRequirementInput.getText() + "\n" +
                "Stealth Disadvantage:  " + stealthDisadvantageToggle.isSelected() + "\n" +
                "Description: " +
                "\n\n" + armorDescription.getText() + "\"\n" +
                "},]\n" +
                "}";
    }

    public void writeArmorToFile() throws IOException {
        JsonFileMaker jsonFileMaker = new JsonFileMaker();
        String itemDetails = collectArmorDetails();
        if (!itemDetails.isEmpty()) {
            jsonFileMaker.writeHomebrewToFile(itemDetails);
            GUI.displayItemCreatedAlert();
        }
        clearAllInput();
    }

    public void clearAllInput() {
        TextField[] textFields = {armorNameInput, armorClassInput, strengthRequirementInput};
        for (TextField field: textFields) {
            field.setText("");
        }
        armorDescription.setText("");
        armorRarityChoice.getSelectionModel().select(1);
        dexModChoice.getSelectionModel().select(0);
        categoryChoice.getSelectionModel().select(0);
        attunementToggle.setSelected(false);
        stealthDisadvantageToggle.setSelected(false);
        requiresAttunementLabel.setVisible(false);
        stealthDisadvantageLabel.setVisible(false);
    }

}
