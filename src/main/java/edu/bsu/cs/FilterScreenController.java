package edu.bsu.cs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class FilterScreenController implements Initializable {
    @FXML
    private VBox equipmentBox;
    @FXML
    private CheckBox attunementCheckBox;
    @FXML
    protected Slider weightSlider;
    @FXML
    private Slider raritySlider;

    private ConfigurationTable configurationTable = new ConfigurationTable();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeFiltersToSetValues();
        setSliderFormat();
    }

    private void initializeFiltersToSetValues() {
        String configurationString = readConfigurationFile();
        setConfigToSavedValuesIfNotNull(configurationString);
    }

    private String readConfigurationFile() {
        try {
            return new ConfigurationFileReader().readConfigFileAsString();
        } catch (Exception ConfigException) {
            return null;
        }
    }

    private void setConfigToSavedValuesIfNotNull(String configurationString) {
        if (configurationString != null) {
            setConfigurationValues(configurationString);
        }
    }

    private void setConfigurationValues(String configurationString) {
        configurationString = configurationString.replace(" ", "");
        String[] configurationValues = configurationString.split(",");
        setGraphicsToValues(configurationValues);

    }

    private void setGraphicsToValues(String[] configurationValues) {
        raritySlider.setValue(Double.parseDouble(configurationValues[0]));
        weightSlider.setValue(Double.parseDouble(configurationValues[1]));
        setCheckboxValues(configurationValues);
        attunementCheckBox.setSelected(Boolean.parseBoolean(configurationValues[7]));
    }

    private void setCheckboxValues(String[] configurationValues) {
        for (int i = 1; i < equipmentBox.getChildren().size(); i++) {
            CheckBox checkBox = (CheckBox) equipmentBox.getChildren().get(i);
            checkBox.setSelected(Boolean.parseBoolean(configurationValues[i+1]));
        }
    }

    private void setSliderFormat() {
        raritySlider.setLabelFormatter(setRaritySliderFormat());
        weightSlider.setLabelFormatter(setWeightSliderFormat());
    }

    private StringConverter<Double> setRaritySliderFormat() {
        return new StringConverter<>() {
            @Override
            public String toString(Double sliderValue) {
               return setRaritySliderLabels(sliderValue);
            }

            @Override
            public Double fromString(String s) {
                return 0.0;
            }
        };
    }

    private String setRaritySliderLabels(Double sliderValue) {
        return switch (sliderValue.toString()){
            case "0.0" -> "Mundane";
            case "1.0" -> "Common";
            case "2.0" -> "Uncommon";
            case "3.0" -> "Rare";
            case "4.0" -> "Very Rare";
            case "5.0" -> "Legendary";
            case "6.0" -> "Artifact";
            default -> "Unknown";
        };
    }

    private StringConverter<Double> setWeightSliderFormat() {
        return new StringConverter<>() {
            @Override
            public String toString(Double sliderValue) {
                return setWeightSliderLabels(sliderValue);
            }

            @Override
            public Double fromString(String s) {
                return 0.0;
            }
        };
    }

    private String setWeightSliderLabels(Double sliderValue) {
        return switch (sliderValue.toString()) {
            case "0.0" -> "Mostly Common";
            case "0.25" -> "More Common";
            case "0.5" -> "No Weight";
            case "0.75" -> "More Rare";
            case "1.0" -> "Mostly Rare";
            default -> sliderValue.toString();
        };
    }

    public void goBackToMain() throws IOException {
        saveConfigurationToFile();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/MainScreen.fxml")));
        GUI.stage.getScene().setRoot(root);
    }

    public void goToHomebrew() throws IOException {
        saveConfigurationToFile();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/HomebrewScreen.fxml")));
        GUI.stage.getScene().setRoot(root);
    }

    private void saveConfigurationToFile() throws IOException {
        configurationTable = setConfigurationTable();
        new ConfigurationFileWriter().writeConfigurationFile(configurationTable);
    }

    private ConfigurationTable setConfigurationTable() {
        configurationTable.clear();

        saveSliders();
        saveCheckboxValues(configurationTable);

        return configurationTable;
    }

    private void saveSliders() {
        configurationTable.put("rarity", String.valueOf(raritySlider.getValue()));
        configurationTable.put("weight", String.valueOf(weightSlider.getValue()));
    }

    private void saveCheckboxValues(ConfigurationTable configurationTable) {

        configurationTable.put("armor", String.valueOf(((CheckBox) equipmentBox.getChildren().get(1)).isSelected()));
        configurationTable.put("weapons", String.valueOf(((CheckBox) equipmentBox.getChildren().get(2)).isSelected()));
        configurationTable.put("magicEquipment", String.valueOf(((CheckBox) equipmentBox.getChildren().get(3)).isSelected()));
        configurationTable.put("magicMisc", String.valueOf(((CheckBox) equipmentBox.getChildren().get(4)).isSelected()));
        configurationTable.put("potions", String.valueOf(((CheckBox) equipmentBox.getChildren().get(5)).isSelected()));
        configurationTable.put("requiresAttunement", String.valueOf(attunementCheckBox.isSelected()));
    }

    @FXML
    private void setToDefault(){
        String defaultValues = "6, .5, true, true, true, true, true, true";
        setConfigurationValues(defaultValues);
    }
}
