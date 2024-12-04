package edu.bsu.cs;

import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;

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

    public String collectMiscItemDetails() {
        String[] checkedInputs = {itemNameInput.getText()};
        if (!ErrorHandler.verifyHomebrewInputsNotBlank(checkedInputs)) {
            GUI.displayHomebrewMiscFieldsAlert();
            return "";
        }
        return "{\n" +
                "\t\"Item_Type\": \"" + itemTypeChoice.getValue() + " HB\",\n" +
                "\t\"Name\": \"" + itemNameInput.getText() + "\",\n" +
                "\t\"Rarity\": \"" + rarityChoice.getValue() + "\",\n" +
                "\t\"Attunement\": " + attunementToggle.isSelected() + ",\n" +
                "\t\"Description\":" +
                "\n\n\t\t\"" + itemDescription.getText() + "\"\n" +
                "},]\n" +
                "}";
    }

    public void writeMiscItemToFile() throws IOException {
        String itemDetails = collectMiscItemDetails();
        if (!itemDetails.isEmpty()) {
            new HomebrewFileMaker().writeHomebrewToFile(itemDetails);
            GUI.displayItemCreatedAlert();
        }
        clearAllInput();
    }

    public void clearAllInput() {
        TextField[] textFields = {itemNameInput};
        for (TextField field : textFields) {
            field.setText("");
        }
        itemDescription.setText("");
        rarityChoice.getSelectionModel().select(1);
        itemTypeChoice.getSelectionModel().select(6);
        attunementToggle.setSelected(false);
        requiresAttunementLabel.setVisible(false);

    }
}
