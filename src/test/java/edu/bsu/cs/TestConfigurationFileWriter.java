package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Hashtable;

public class TestConfigurationFileWriter {

    Hashtable<String, String> testConfigurationValues = new Hashtable<>();

    @Test
    public void testInitializeConfigurationFile() throws IOException, URISyntaxException {
        Files.createDirectories(Paths.get("src/test/resources/dataFiles"));

        ConfigurationFileWriter configWriter = new ConfigurationFileWriter();

        initializeTestHastTable();
        configWriter.initializeConfigFile(testConfigurationValues);
    }

    private void initializeTestHastTable() {
        testConfigurationValues.put("rarity", "Artifact");
        testConfigurationValues.put("weight", "1.0");
        testConfigurationValues.put("armor", "True");
        testConfigurationValues.put("weapons", "True");
        testConfigurationValues.put("magicEquipment", "True");
        testConfigurationValues.put("magicMisc", "True");
        testConfigurationValues.put("potions", "True");
        testConfigurationValues.put("requiresAttunement", "True");
    }

}
