package edu.bsu.cs;

import java.io.IOException;

public class ItemFilter {

    ConfigurationFileReader configFileReader = new ConfigurationFileReader();


    protected int checkForMaxRarityPermitted() throws IOException {
        return Integer.parseInt(configFileReader.fetchConfigValues()[0]);
    }

    protected double fetchWeight() throws IOException {
        return Double.parseDouble(configFileReader.fetchConfigValues()[1]);
    }

    protected Boolean checkForItemTypeEnabled(String configValue) throws IOException {
        return Boolean.parseBoolean(fetchItemTypeConfigValue(configValue));
    }

    private String fetchItemTypeConfigValue(String configValue) throws IOException {
        return switch (configValue){
            case "armor" -> configFileReader.fetchConfigValues()[2].strip();
            case "weapon" -> configFileReader.fetchConfigValues()[3].strip();
            case "magicEquipment" -> configFileReader.fetchConfigValues()[4].strip();
            case "magicMisc" -> configFileReader.fetchConfigValues()[5].strip();
            case "potions" -> configFileReader.fetchConfigValues()[6].strip();
            default -> throw new IllegalStateException("Unexpected item type: " + configValue);
        };
    }
}
