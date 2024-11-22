package edu.bsu.cs;

import java.io.IOException;

public class ItemFilter {

    protected int checkForMaxRarityPermitted() throws IOException {
        return Integer.parseInt(new ConfigurationFileReader().fetchConfigValues()[0]);
    }

    protected Boolean checkForItemTypeEnabled(String configValue) throws IOException {
        return Boolean.parseBoolean(fetchItemTypeConfigValue(configValue));
    }

    private String fetchItemTypeConfigValue(String configValue) throws IOException {
        return switch (configValue){
            case "armor" -> new ConfigurationFileReader().fetchConfigValues()[3].strip();
            case "weapon" -> new ConfigurationFileReader().fetchConfigValues()[4].strip();
            case "magicEquipment" -> new ConfigurationFileReader().fetchConfigValues()[5].strip();
            case "magicMisc" -> new ConfigurationFileReader().fetchConfigValues()[6].strip();
            case "potions" -> new ConfigurationFileReader().fetchConfigValues()[7].strip();
            default -> throw new IllegalStateException("Unexpected item type: " + configValue);
        };
    }


}
