package edu.bsu.cs;

import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestStandardItemParser {
    StandardItemsParser testStandardParser = new StandardItemsParser();

    @Test
    public void testGetStandardItemNameFromSampleJson() throws IOException {
        InputStream testInputStream = getWeaponItemJsonFile();
        JSONArray sampleJsonArray = testStandardParser.parseStandardItemName(JsonToString.readJsonAsString(testInputStream));
        String[] expected = {"Battleaxe","Blowgun","Club","Crossbow, hand","Crossbow, heavy"};
        String[] result = new String[5];
        for (int i = 0; i < 5; i++) {
            result[i] = sampleJsonArray.get(i).toString();
        }
        Assertions.assertArrayEquals(expected,result);
    }

    @Test
    public void testGetStatsFromSampleJson() throws IOException {
        InputStream testInputStream = getWeaponItemJsonFile();
        JSONArray sampleJsonArray = testStandardParser.parseStandardItemResults(JsonToString.readJsonAsString(testInputStream));
        String expected = "url=https://api.open5e.com/v2/weapons/srd_battleaxe/";
        String result = sampleJsonArray.getFirst().toString().substring(1, 53);
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void testParseArmorClassDisplay() throws IOException {
        InputStream testInputStream = getArmorItemJsonFile();
        JSONArray sampleJsonArray = testStandardParser.parseArmorClassDisplay(JsonToString.readJsonAsString(testInputStream));
        String[] expected = listOfArmorClassDisplay();
        String [] result = new String[12];
        for (int i = 0; i < 12; i++) {
            result[i] = sampleJsonArray.get(i).toString();
        }
        Assertions.assertArrayEquals(expected,result);
    }

    @Test
    public void testParseArmorCategory() throws IOException {
        InputStream testInputStream = getArmorItemJsonFile();
        JSONArray sampleJsonArray = testStandardParser.parseArmorCategory(JsonToString.readJsonAsString(testInputStream));
        String[] expected = {"medium","heavy","medium","medium","medium","light","light","heavy","heavy","medium","heavy","light"};
        String [] result = new String[12];
        for (int i = 0; i < 12; i++) {
            result[i] = sampleJsonArray.get(i).toString();
        }
        Assertions.assertArrayEquals(expected,result);
    }

    @Test
    public void testParseStealthDisadvantage() throws IOException {
        InputStream testInputStream = getArmorItemJsonFile();
        JSONArray sampleJsonArray = testStandardParser.parseStealthDisadvantage(JsonToString.readJsonAsString(testInputStream));
        String[] expected = {"false","true","false","true","false","false","true","true","true","true","true","false"};
        String [] result = new String[12];
        for (int i = 0; i < 12; i++) {
            result[i] = sampleJsonArray.get(i).toString();
        }
        Assertions.assertArrayEquals(expected,result);
    }

    private InputStream getWeaponItemJsonFile(){
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("SampleWeapons.json");
        assert inputStream != null;
        return inputStream;
    }

    private InputStream getArmorItemJsonFile(){
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("SampleArmor.json");
        assert inputStream != null;
        return inputStream;
    }

    private String[] listOfArmorClassDisplay(){
        return new String[]{"14 + Dex modifier (max 2)","16","13 + Dex modifier (max 2)","15 + Dex modifier (max 2)","12 + Dex modifier (max 2)","11 + Dex modifier","11 + Dex modifier","18","14","14 + Dex modifier (max 2)","17","12 + Dex modifier"};
    }

}
