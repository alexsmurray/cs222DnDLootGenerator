package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestItemFilter {

    private final ItemFilter testItemFilter = new ItemFilter();

    @Test
    public void testCheckForWeaponsEnabled() throws IOException {
        setConfigFileToDefault();
        Boolean result = testItemFilter.checkForWeaponsEnabled();

        Assertions.assertTrue(result);
    }

    private void setConfigFileToDefault() throws IOException {
        Files.createDirectories(Paths.get("src/main/resources/dataFiles"));
        new ConfigurationFileWriter().writeConfigurationFile(new ConfigurationTable());
    }
}
