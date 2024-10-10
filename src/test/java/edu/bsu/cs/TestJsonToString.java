package edu.bsu.cs;

import net.minidev.json.JSONArray;
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
    public void testForItemRequiresAttunment(){
        String expected = "True";
        String result = InputFormatter.formatAttunment("requires attunement");
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void testForItemDoesNotRequiresAttunment(){
        String expected = "False";
        String result = InputFormatter.formatAttunment("");
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void testShuffleMethod(){
        JSONArray jsonArray = createJsonArray();
        JSONArray shuffledJsonArray = InputFormatter.shuffleJsonArray(createJsonArray());
        StringBuilder shuffledArray = new StringBuilder();
        StringBuilder unshuffledArray = new StringBuilder();
        for (Object object : jsonArray) {
            unshuffledArray.append(object);
        }
        for (Object o : shuffledJsonArray) {
            shuffledArray.append(o);
        }
        Assertions.assertNotEquals(unshuffledArray.toString(),shuffledArray.toString());
    }

    private JSONArray createJsonArray(){
        JSONArray array = new JSONArray();
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.add(5);
        return array;
    }









}
