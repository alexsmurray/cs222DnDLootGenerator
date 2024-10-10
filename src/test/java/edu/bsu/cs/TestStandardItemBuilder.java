package edu.bsu.cs;

import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestStandardItemBuilder {
    @Test
    public void testSelectRandomItemGeneratesIndexInRange() {
        JSONArray testJsonArray = createJsonArray();
        int testIndex = StandardItemBuilder.selectRandomItemIndex(testJsonArray);
        Assertions.assertTrue(testIndex >= 0 && testIndex < testJsonArray.size());
    }


    private JSONArray createJsonArray(){
        JSONArray array = new JSONArray();
        array.add("A");
        array.add("B");
        array.add("C");
        array.add("D");
        array.add("E");
        return array;
    }
}
