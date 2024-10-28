package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestOutputFormatter {

    @Test
    public void testForRareItem(){
        String expected = "Rare";
        String result = OutputFormatter.formatRarity("rare");
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void testForVeryRareItem(){
        String expected = "Very Rare";
        String result = OutputFormatter.formatRarity("very rare");
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void testForItemRequiresAttunement(){
        String expected = "True";
        String result = OutputFormatter.formatAttunement("requires attunement");
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void testForItemDoesNotRequireAttunement(){
        String expected = "False";
        String result = OutputFormatter.formatAttunement("");
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void testFormatItemForList(){
        String expectedString = "Name        Rarity        Type    *";
        String result = OutputFormatter.formatItemForList(new Item("Name", "Rarity", "Type", "True", "Desc", "Stats"));
        Assertions.assertEquals(expectedString, result);
    }

    @Test
    public void testFormatDateTime(){
        String expected = "test at home";
        String output = OutputFormatter.formatDateTime("testThome");
        Assertions.assertEquals(expected,output);
    }

}
