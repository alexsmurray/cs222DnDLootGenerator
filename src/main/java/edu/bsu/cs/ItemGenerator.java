package edu.bsu.cs;

import java.io.IOException;
import java.util.*;

public class ItemGenerator {

    protected List<Item> generateAmountOfItems(int numberOfItemsToGenerate) throws IOException {
        List<Item> filteredItemList = new ArrayList<>();
        List<Item> displayItemList = new ArrayList<>();
        new ItemListBuilder(filteredItemList);

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
