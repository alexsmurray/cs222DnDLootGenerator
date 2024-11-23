package edu.bsu.cs;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class TestItemBuilder {

    @Test
    public void testGenerateAmountOfItems() throws IOException {
        List<Item> result = new ItemBuilder().generateAmountOfItems(5);
        Assertions.assertEquals(5, result.size());
    }

    @Test
    public void testGenerateAmountOfItemReturnsValues() throws IOException {
        List<Item> result = new ItemBuilder().generateAmountOfItems(5);
        Assertions.assertFalse(result.getFirst().getName().isBlank());
        Assertions.assertFalse(result.getFirst().getRarity().isBlank());
        Assertions.assertFalse(result.getFirst().getType().isBlank());
        Assertions.assertFalse(result.getFirst().getAttunement().isBlank());
        Assertions.assertFalse(result.getFirst().getDetails().isBlank());
    }
}
