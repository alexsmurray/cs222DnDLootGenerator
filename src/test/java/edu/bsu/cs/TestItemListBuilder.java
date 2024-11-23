package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestItemListBuilder {

    List<Item> itemsList = new ArrayList<>();
    private final ItemListBuilder itemListBuilder = new ItemListBuilder(itemsList);
    private final List<Item> testItemList = new ArrayList<>();

    public TestItemListBuilder() throws IOException {}

    @Test
    public void testPopulateListOfItems() throws IOException {
        testItemList.clear();
        itemListBuilder.populateListOfItems(testItemList);

        String firstMagicItem = testItemList.getFirst().getName();
        String secondToLastMagicItem = testItemList.get(testItemList.size()-2).getName();
        String[] result = {firstMagicItem, secondToLastMagicItem};
        String[] expected = {"Breastplate", "Zipline Ring"};

        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testPopulateListWithArmorItems() throws IOException {
        testItemList.clear();
        itemListBuilder.populateListWithArmorItems(testItemList);
        String expected = "Breastplate";

        Assertions.assertEquals(expected, testItemList.getFirst().getName());
    }

    @Test
    public void testPopulateListWithWeaponItems() throws IOException {
        testItemList.clear();
        itemListBuilder.populateListWithWeaponItems(testItemList);
        String expected = "Battleaxe";

        Assertions.assertEquals(expected, testItemList.getFirst().getName());
    }

    @Test
    public void testPopulateListWithMagicItems() throws IOException {
        testItemList.clear();
        itemListBuilder.populateListWithMagicItems(testItemList);
        String expected = "Aberrant Agreement";

        Assertions.assertEquals(expected, testItemList.getFirst().getName());
    }

    @Test
    public void testFetchNumberOfMagicItemPages() throws IOException {
        testItemList.clear();
        int result = itemListBuilder.fetchNumberOfMagicItemPages();

        Assertions.assertEquals(33,result);
    }
}
