package edu.bsu.cs;

import java.io.IOException;

public class ItemFilter {
    protected Boolean checkForWeaponsEnabled() throws IOException {
        String isWeaponsEnabled = fetchWeaponConfigValue();
        return Boolean.parseBoolean(isWeaponsEnabled);
    }

    private String fetchWeaponConfigValue() throws IOException {
        return new ConfigurationFileReader().fetchConfigValues()[4].strip();
    }
}
