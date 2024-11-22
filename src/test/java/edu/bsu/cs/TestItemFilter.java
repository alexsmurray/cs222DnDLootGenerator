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
    public void testPopulateListOfItems() throws IOException {
        List<String> testFilterItemList = new ArrayList<>();
        itemFilter.populateListOfItems(testFilterItemList);

        String firstMagicItem = testFilterItemList.getFirst();
        String secondToLastMagicItem = testFilterItemList.get(testFilterItemList.size()-2);
        String[] result = {firstMagicItem, secondToLastMagicItem};
        String[] expected = {"Breastplate", "Zipline Ring"};

        Assertions.assertArrayEquals(expected, result);
    }

    private void setConfigFileToDefault() throws IOException {
        Files.createDirectories(Paths.get("src/main/resources/dataFiles"));
        new ConfigurationFileWriter().writeConfigurationFile(new ConfigurationTable());
    }
}
