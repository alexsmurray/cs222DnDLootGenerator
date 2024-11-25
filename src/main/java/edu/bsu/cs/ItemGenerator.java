package edu.bsu.cs;

import java.util.*;

public class ItemGenerator {

    protected static List<Item> filteredItemList = new ArrayList<>();

    protected List<Item> generateAmountOfItems(int numberOfItemsToGenerate) {
        List<Item> displayItemList = new ArrayList<>();

        for (int i = 0; i < numberOfItemsToGenerate; i++) {
            displayItemList.add(generateItem(filteredItemList));
        }
        return displayItemList;
    }

    private Item generateItem(List<Item> filteredItemList) {
        int randomItemIndex = new Random().nextInt(filteredItemList.size());
        return filteredItemList.get(randomItemIndex);
    }

}
