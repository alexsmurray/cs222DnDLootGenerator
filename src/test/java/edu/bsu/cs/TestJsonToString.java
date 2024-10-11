package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestJsonToString {

    @Test
    public void testForRareItem(){
        String expected = "Rare";
        String result = InputFormatter.formatRarity("rare");
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void testForVeryRareItem(){
        String expected = "Very Rare";
        String result = InputFormatter.formatRarity("very rare");
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void testForItemRequiresAttunement(){
        String expected = "True";
        String result = InputFormatter.formatAttunement("requires attunement");
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void testForItemDoesNotRequireAttunement(){
        String expected = "False";
        String result = InputFormatter.formatAttunement("");
        Assertions.assertEquals(expected,result);
    }

}
