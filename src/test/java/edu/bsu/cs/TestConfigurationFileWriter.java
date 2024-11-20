package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestConfigurationFileWriter {

    ConfigurationTable testConfigurationValues = new ConfigurationTable();

    @Test
    public void testInitializeConfigurationFile() throws IOException {
        Files.createDirectories(Paths.get("src/test/resources/dataFiles"));
        ConfigurationFileWriter configWriter = new ConfigurationFileWriter();

        configWriter.initializeConfigFile(testConfigurationValues);

        String result = new ConfigurationFileReader().readConfigFileAsString();
        String expected = "6, .5, true, true, true, true, true, true";

        Assertions.assertEquals(expected ,result);
    }


}
