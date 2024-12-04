package edu.bsu.cs;

import java.io.IOException;

public class ItemFilter {

    ConfigurationFileReader configFileReader = new ConfigurationFileReader();

    protected int checkForMaxRarityPermitted() {
        try {
            double rawRarityValue = Double.parseDouble(configFileReader.fetchConfigValues()[0]);
            return (int) rawRarityValue;
        } catch (Exception ignored) {
            return 0;
        }
    }

    protected int fetchWeight() {
        try {
            double rawWeight = Double.parseDouble(new ConfigurationFileReader().fetchConfigValues()[1]);
            return (int) (rawWeight * 40);
        } catch (Exception ignored) {
            return 20;
        }
    }

    protected Boolean checkForItemTypeEnabled(String configValue) throws IOException {
        return Boolean.parseBoolean(fetchItemTypeConfigValue(configValue));
    }

    private String fetchItemTypeConfigValue(String configValue) throws IOException {
        return switch (configValue) {
            case "armor" -> configFileReader.fetchConfigValues()[2].strip();
            case "weapon" -> configFileReader.fetchConfigValues()[3].strip();
            case "magicEquipment" -> configFileReader.fetchConfigValues()[4].strip();
            case "homebrew" -> configFileReader.fetchConfigValues()[5].strip();
            default -> throw new IllegalStateException("Unexpected item type: " + configValue);
        };
    }

}
