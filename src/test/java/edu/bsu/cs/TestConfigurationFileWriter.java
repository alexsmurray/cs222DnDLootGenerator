package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Hashtable;

public class TestConfigurationFileWriter {

    Hashtable<String, String> testConfigurationValues = new Hashtable<>();

    @Test
    public void testInitializeConfigurationFile() throws IOException {
        Files.createDirectories(Paths.get("src/test/resources/dataFiles"));
        ConfigurationFileWriter configWriter = new ConfigurationFileWriter();

        initializeTestHastTable();
        configWriter.initializeConfigFile(testConfigurationValues);

        String result = new ConfigurationFileReader().readConfigFileAsString();
        String expected = "Artifact, 1.0, True, True, True, True, True, True";

        Assertions.assertEquals(expected ,result);
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
