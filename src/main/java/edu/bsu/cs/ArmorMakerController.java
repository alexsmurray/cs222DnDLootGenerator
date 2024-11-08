package edu.bsu.cs;

import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class ArmorMakerController {
    public TextField armorNameInput;
    public Pane armorMaker;
    public ChoiceBox<String> dexModChoice;
    public RadioButton stealthDisadvantageRadio;
    public ToggleGroup stealthDisadvantage;
    public RadioButton noneRadio;
    public TextArea armorDescription;
    public ChoiceBox<String> armorRarityChoice;
    public ChoiceBox<String> categoryChoice;
}
