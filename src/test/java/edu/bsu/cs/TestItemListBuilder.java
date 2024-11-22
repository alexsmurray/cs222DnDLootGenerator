package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestItemListBuilder {

    private final ItemListBuilder itemFilter = new ItemListBuilder();
    private final List<String> testFilterItemList = new ArrayList<>();

    public TestItemListBuilder() throws IOException {}

    @Test
    public void testPopulateListOfItems() throws IOException {
        itemFilter.populateListOfItems(testFilterItemList);

        String firstMagicItem = testFilterItemList.getFirst();
        String secondToLastMagicItem = testFilterItemList.get(testFilterItemList.size()-2);
        String[] result = {firstMagicItem, secondToLastMagicItem};
        String[] expected = {"Breastplate", "Zipline Ring"};

        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testPopulateListWithArmorItems() {
        itemFilter.populateListWithArmorItems(testFilterItemList);
        String expected = "Breastplate";

        Assertions.assertEquals(expected,testFilterItemList.getFirst());
    }

    @Test
    public void testPopulateListWithWeaponItems() {
        itemFilter.populateListWithWeaponItems(testFilterItemList);
        String expected = "Battleaxe";

        Assertions.assertEquals(expected,testFilterItemList.getFirst());
    }

    @Test
    public void testPopulateListWithMagicItems() throws IOException {
        itemFilter.populateListWithMagicItems(testFilterItemList);
        String expected = "Aberrant Agreement";

        Assertions.assertEquals(expected,testFilterItemList.getFirst());
    }

    @Test
    public void testFetchNumberOfMagicItemPages() throws IOException {
        int result = itemFilter.fetchNumberOfMagicItemPages();

        Assertions.assertEquals(33,result);
    }
}
