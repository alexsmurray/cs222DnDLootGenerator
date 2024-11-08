package edu.bsu.cs;

import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class MiscItemMakerController {
    public Pane armorMaker;
    public TextField itemNameInput;
    public TextArea itemDescription;
    public ToggleButton attunementToggle;
    public ChoiceBox<String> rarityChoice;
    public Label requiresAttunementLabel;
    public ChoiceBox<String> itemTypeChoice;

    public void checkAttunement() {
        requiresAttunementLabel.setVisible(attunementToggle.isSelected());
    }
}
