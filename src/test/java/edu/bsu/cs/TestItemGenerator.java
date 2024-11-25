package edu.bsu.cs;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class TestItemGenerator {

    ConfigurationTable testConfigurationTable = new ConfigurationTable();

    @Test
    public void testGenerateAmountOfItems() throws IOException {
        setDefaultConfigSettings();

        List<Item> result = new ItemGenerator().generateAmountOfItems(5);
        Assertions.assertEquals(5, result.size());
    }

    @Test
    public void testGenerateAmountOfItemReturnsValues() throws IOException {
        setDefaultConfigSettings();

        List<Item> result = new ItemGenerator().generateAmountOfItems(5);
        Assertions.assertFalse(result.getFirst().getName().isBlank());
        Assertions.assertFalse(result.getFirst().getRarity().isBlank());
        Assertions.assertFalse(result.getFirst().getType().isBlank());
        Assertions.assertFalse(result.getFirst().getAttunement().isBlank());
        Assertions.assertFalse(result.getFirst().getDetails().isBlank());
    }

    private void setDefaultConfigSettings() throws IOException {
        ConfigurationFileWriter testConfigWriter = new ConfigurationFileWriter();
        testConfigWriter.initializeConfigFile(testConfigurationTable);
        new ItemListBuilder(ItemGenerator.filteredItemList);
    }
}
