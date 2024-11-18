package edu.bsu.cs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Slider;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class FilterScreenController implements Initializable {

    @FXML
    private Slider weightSlider;
    @FXML
    private Slider raritySlider;

    @FXML
    private void onChangeRaritySliderLabel() {
        //on change set filtering to less than or equal to the selected rarity
    }

    @FXML
    private void onChangeWeightSlider() {
        //on change set weight to desired amount
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setSliderFormat();
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
            case "0.0" -> "No Rares";
            case "0.25" -> "Few Rares";
            case "0.5" -> "No Weight";
            case "0.75" -> "More Rares";
            case "1.0" -> "Very Rare";
            default -> sliderValue.toString();
        };
    }

    public void goBackToMain() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/MainScreen.fxml")));
        GUI.stage.getScene().setRoot(root);
    }
    public void goToHomebrew() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/HomebrewScreen.fxml")));
        GUI.stage.getScene().setRoot(root);
    }

}
