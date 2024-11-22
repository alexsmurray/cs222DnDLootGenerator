package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestItemListBuilder {

    private final ItemListBuilder itemListBuilder = new ItemListBuilder();
    private final List<String> testFilterItemList = new ArrayList<>();

    public TestItemListBuilder() throws IOException {}

    @Test
    public void testPopulateListOfItems() throws IOException {
        testFilterItemList.clear();
        itemListBuilder.populateListOfItems(testFilterItemList);

        String firstMagicItem = testFilterItemList.getFirst();
        String secondToLastMagicItem = testFilterItemList.get(testFilterItemList.size()-2);
        String[] result = {firstMagicItem, secondToLastMagicItem};
        String[] expected = {"Breastplate", "Zipline Ring"};

        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testPopulateListWithArmorItems() throws IOException {
        testFilterItemList.clear();
        itemListBuilder.populateListWithArmorItems(testFilterItemList);
        String expected = "Breastplate";

        Assertions.assertEquals(expected,testFilterItemList.getFirst());
    }

    @Test
    public void testPopulateListWithWeaponItems() throws IOException {
        testFilterItemList.clear();
        itemListBuilder.populateListWithWeaponItems(testFilterItemList);
        String expected = "Battleaxe";

        Assertions.assertEquals(expected,testFilterItemList.getFirst());
    }

    @Test
    public void testPopulateListWithMagicItems() throws IOException {
        testFilterItemList.clear();
        itemListBuilder.populateListWithMagicItems(testFilterItemList);
        String expected = "Aberrant Agreement";

        Assertions.assertEquals(expected,testFilterItemList.getFirst());
    }

    @Test
    public void testFetchNumberOfMagicItemPages() throws IOException {
        testFilterItemList.clear();
        int result = itemListBuilder.fetchNumberOfMagicItemPages();

        Assertions.assertEquals(33,result);
    }
}
