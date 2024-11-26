package edu.bsu.cs;

import java.util.Hashtable;

public class ConfigurationTable extends Hashtable<String, String> {

    public ConfigurationTable() {
        put("rarity", "0");
        put("weight", ".5");
        put("armor", "true");
        put("weapons", "true");
        put("magicEquipment", "true");
        put("magicMisc", "true");
        put("potions", "true");
        put("requiresAttunement", "true");
        put("homebrew", "true");
    }
}
