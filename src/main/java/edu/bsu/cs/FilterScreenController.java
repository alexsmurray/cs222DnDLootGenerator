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
    private Slider raritySlider;

    public void changeSliderLabel() {
        //on change set to less than or equal to the selected rarity
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        raritySlider.setLabelFormatter(setSliderFormat());
    }

    private StringConverter<Double> setSliderFormat() {
        return new StringConverter<>() {
            @Override
            public String toString(Double sliderValue) {
               return setSliderLabels(sliderValue);
            }

            @Override
            public Double fromString(String s) {
                return 0.0;
            }
        };
    }

    private String setSliderLabels(Double sliderValue) {
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

    public void goBackToMain() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/MainScreen.fxml")));
        GUI.stage.getScene().setRoot(root);
    }
    public void goToHomebrew() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/HomebrewScreen.fxml")));
        GUI.stage.getScene().setRoot(root);
    }



}
