package edu.bsu.cs;

import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestJsonParser {

    JsonParser testParser = new JsonParser();

    @Test
    public void testGetMagicItemNameFromSampleJson() throws IOException {
        InputStream testInputStream = getJsonFile();
        JSONArray sampleJsonArray = testParser.parseMagicItemName(JsonToString.readJsonAsString(testInputStream), 0);
        String[] expected = {"Aberrant Agreement","Absurdist Web","Accursed Idol","Adamantine Armor","Aegis of the Eternal Moon"};
        String[] result = new String[5];
        for (int i = 0; i < 5; i++) {
            result[i] = sampleJsonArray.get(i).toString();
        }
        Assertions.assertArrayEquals(expected,result);
    }

    @Test
    public void testGetRarityFromSampleJson() throws IOException {
        InputStream testInputStream = getJsonFile();
        JSONArray sampleJsonArray = testParser.parseMagicItemRarity(JsonToString.readJsonAsString(testInputStream), 0);
        String[] expected = {"Rare","Very Rare","Uncommon","Uncommon","Very Rare"};
        String[] result = new String[5];
        for (int i = 0; i < 5; i++) {
            result[i] = OutputFormatter.formatRarity(sampleJsonArray.get(i).toString());
        }
        Assertions.assertArrayEquals(expected,result);
    }

    @Test
    public void testGetTypeFromSampleJson() throws IOException {
        InputStream testInputStream = getJsonFile();
        JSONArray sampleJsonArray = testParser.parseMagicItemType(JsonToString.readJsonAsString(testInputStream), 0);
        String[] expected = {"Scroll","Wondrous Item","Wondrous item","Armor (medium or heavy)","Armor"};
        String[] result = new String[5];
        for (int i = 0; i < 5; i++) {
            result[i] = sampleJsonArray.get(i).toString();
        }
        Assertions.assertArrayEquals(expected,result);
    }

    @Test
    public void testGetAttunementFromSampleJson() throws IOException {
        InputStream testInputStream = getJsonFile();
        JSONArray sampleJsonArray = testParser.parseMagicItemAttunement(JsonToString.readJsonAsString(testInputStream), 0);
        String[] expected = {"False","False","True","False","True"};
        String [] result = new String[5];
        for (int i = 0; i < 5; i++) {
            result[i] = OutputFormatter.formatAttunement(sampleJsonArray.get(i).toString());
        }
        Assertions.assertArrayEquals(expected,result);
    }

    private InputStream getJsonFile(){
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("SampleMagicItem.json");
        assert inputStream != null;
        return inputStream;
    }

}
