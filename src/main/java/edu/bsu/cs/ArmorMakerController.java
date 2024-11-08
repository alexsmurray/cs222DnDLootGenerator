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

    public void checkAttunement() {
        requiresAttunementLabel.setVisible(attunementToggle.isSelected());
    }
    public void checkStealthDisadvantage() {
        stealthDisadvantageLabel.setVisible(stealthDisadvantageToggle.isSelected());
    }
}
