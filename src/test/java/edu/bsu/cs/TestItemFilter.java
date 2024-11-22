package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestItemFilter {

    private final ItemFilter itemFilter = new ItemFilter();

    public TestItemFilter() throws IOException {}

    @Test
    public void populateListOfItems() throws IOException {
        List<String> testFilterItemList = new ArrayList<>();
        itemFilter.populateListOfItems(testFilterItemList);

        String result = testFilterItemList.getFirst();
        String expected = "Breastplate";

        Assertions.assertEquals(expected, result);
    }

    private void setConfigFileToDefault() throws IOException {
        Files.createDirectories(Paths.get("src/main/resources/dataFiles"));
        new ConfigurationFileWriter().writeConfigurationFile(new ConfigurationTable());
    }
}
