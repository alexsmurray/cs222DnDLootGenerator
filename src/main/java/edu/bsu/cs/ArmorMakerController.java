package edu.bsu.cs;

import javafx.fxml.FXML;
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
    public TextField armorClassInput;
    public TextField strengthRequirementInput;

    public HomebrewScreenController homebrewScreenController = new HomebrewScreenController();

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

    protected String collectArmorDetails() {
        String[] checkedInputs = {armorNameInput.getText(), armorClassInput.getText(), strengthRequirementInput.getText()};

        if (!ErrorHandler.verifyHomebrewInputsNotBlank(checkedInputs)) {
            GUI.displayHomebrewArmorFieldsAlert();
            return "";
        }

        return "{\n" +
                "\t\"Item_Type\": \"Armor HB\",\n" +
                "\t\"Name\": \"" + armorNameInput.getText() + "\",\n" +
                "\t\"Rarity\": \"" + armorRarityChoice.getValue() + "\",\n" +
                "\t\"Attunement\": " + attunementToggle.isSelected() + ",\n" +
                "\t\"Description\":" +
                "\n\t\t\"AC:  " + armorClassInput.getText() + OutputFormatter.formatDexModifier(dexModChoice.getValue()) + "\n" +
                "\t\tCategory:  " + categoryChoice.getValue() + "\n" +
                "\t\tStrength Requirement:  " + strengthRequirementInput.getText() + "\n" +
                "\t\tStealth Disadvantage:  " + stealthDisadvantageToggle.isSelected() + "\n" +
                "\n\t\tDescription: " +
                "\n\n\t\t" + armorDescription.getText() + "\"\n" +
                "},]\n" +
                "}";
    }

    @FXML
    protected void writeArmorToFile() throws IOException {
        String itemDetails = collectArmorDetails();
        displayAlertIfNotEmpty(itemDetails);
        clearAllInput();
    }

    private static void displayAlertIfNotEmpty(String itemDetails) throws IOException {
        if (!itemDetails.isEmpty()) {
            new HomebrewFileMaker().writeHomebrewToFile(itemDetails);
            GUI.displayItemCreatedAlert();
        }
    }

    public void clearAllInput() {
        TextField[] textFields = {armorNameInput, armorClassInput, strengthRequirementInput};
        for (TextField field : textFields) {
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
