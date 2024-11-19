package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Hashtable;


public class TestConfigurationFileReader {

    Hashtable<String, String> testConfigurationValues = new Hashtable<>();

    @Test
    public void testReadConfigurationFile() throws IOException {
        ConfigurationFileReader testConfigReader = new ConfigurationFileReader();
        createConfigFile();

        String result = testConfigReader.readConfigFileAsString();
        String expected = "Artifact, 1.0, True, True, True, True, True, True";

        Assertions.assertEquals(expected, result);

    }

    private void createConfigFile() throws IOException {
        ConfigurationFileWriter testConfigWriter = new ConfigurationFileWriter();
        initializeTestHastTable();
        testConfigWriter.initializeConfigFile(testConfigurationValues);
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
