package edu.bsu.cs;

import javafx.scene.control.*;
import javafx.scene.layout.Pane;

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

}
