package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class TestItemListBuilder {

    List<Item> itemsList = new ArrayList<>();
    private ItemListBuilder itemListBuilder = new ItemListBuilder(itemsList);
    private final List<Item> testItemList = new ArrayList<>();

    public TestItemListBuilder() throws IOException, URISyntaxException {
        setHomeBrewToDefault();
        verifyDataFilesExist();
    }

    private void verifyDataFilesExist() throws IOException, URISyntaxException {
        try {
            itemListBuilder.populateListOfItems(testItemList);
        } catch (Exception FilesMissingInTestsException) {
            JsonFileMaker.updateAPIFiles();
        }
    }

    @Test
    public void testPopulateListOfItems() throws IOException{
        testItemList.clear();
        itemListBuilder = new ItemListBuilder(testItemList);
        createConfigFile();

        String firstMagicItem = testItemList.getFirst().getName();
        String secondToLastMagicItem = testItemList.get(testItemList.size()-2).getName();
        String[] result = {firstMagicItem, secondToLastMagicItem};
        String[] expected = {"Breastplate", "Zipline Ring"};

        Assertions.assertArrayEquals(expected, result);
    }

    private void setHomeBrewToDefault() throws IOException{
        FileWriter homebrewFile = new FileWriter("src/main/resources/dataFiles/homebrew.txt");
        homebrewFile.write("""
                    {
                    "results": [{
                    \t"Item_Type": "Homebrew Item",
                    \t"Name": null,
                    \t"Rarity": "Non-existent",
                    \t"Attunement": false,
                    \t"Description": "YOU SHOULD NOT BE HERE",
                    },]
                    }""");
        homebrewFile.close();
    }

    @Test
    public void testPopulateListWithArmorItems() throws IOException {
        testItemList.clear();
        itemListBuilder.populateListWithArmorItems(testItemList);
        createConfigFile();
        String expected = "Breastplate";

        Assertions.assertEquals(expected, testItemList.getFirst().getName());
    }

    @Test
    public void testPopulateListWithWeaponItems() throws IOException {
        testItemList.clear();
        itemListBuilder.populateListWithWeaponItems(testItemList);
        createConfigFile();
        String expected = "Battleaxe";

        Assertions.assertEquals(expected, testItemList.getFirst().getName());
    }

    @Test
    public void testPopulateListWithMagicItems() throws IOException {
        testItemList.clear();
        itemListBuilder.populateListWithMagicItems(testItemList);
        createConfigFile();


        String expected = "Aberrant Agreement";

        Assertions.assertEquals(expected, testItemList.getFirst().getName());
    }

    @Test
    public void testFetchNumberOfMagicItemPages() throws IOException {
        testItemList.clear();
        int result = itemListBuilder.fetchNumberOfMagicItemPages();

        Assertions.assertEquals(33,result);
    }

    private void createConfigFile() throws IOException {
        ConfigurationFileWriter testConfigWriter = new ConfigurationFileWriter();
        testConfigWriter.initializeConfigFile(new ConfigurationTable());
    }
}
