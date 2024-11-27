package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;


public class TestConfigurationFileReader {

    ConfigurationTable testConfigurationTable = new ConfigurationTable();

    @Test
    public void testReadConfigurationFile() throws IOException {
        ConfigurationFileReader testConfigReader = new ConfigurationFileReader();
        createConfigFile();

        String result = testConfigReader.readConfigFileAsString();
        String expected = "0, .5, true, true, true, true";

        Assertions.assertEquals(expected, result);

    }

    @Test
    public void testFetchConfigValues() throws IOException {
        createConfigFile();
        String[] result = new ConfigurationFileReader().fetchConfigValues();
        String[] expected = {"0"," .5"," true"," true"," true", " true"};

        Assertions.assertArrayEquals(expected, result);
    }

    private void createConfigFile() throws IOException {
        ConfigurationFileWriter testConfigWriter = new ConfigurationFileWriter();
        testConfigWriter.initializeConfigFile(testConfigurationTable);
    }
}
