package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestRarityToIntegerConverter {

    @Test
    public void testDetermineRarityValue() {
        int rarityValue = RarityToIntegerConverter.determineRarityValue("Mundane");
        Assertions.assertEquals(6, rarityValue);
    }
}
