package edu.bsu.cs;

import java.util.Hashtable;

public class ConfigurationTable extends Hashtable<String, String> {

    public ConfigurationTable() {
        put("rarity", "Artifact");
        put("weight", "1.0");
        put("armor", "True");
        put("weapons", "True");
        put("magicEquipment", "True");
        put("magicMisc", "True");
        put("potions", "True");
        put("requiresAttunement", "True");
    }
}
