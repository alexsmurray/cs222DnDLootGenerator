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
        String[] expected = {"Battleaxe", "Blowgun", "Club", "Crossbow, hand", "Crossbow, heavy"};
        String[] result = new String[5];
        for (int i = 0; i < 5; i++) {
            result[i] = sampleJsonArray.get(i).toString();
        }
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testGetStatsFromSampleJson() throws IOException {
        InputStream testInputStream = getWeaponItemJsonFile();
        JSONArray sampleJsonArray = testStandardParser.parseStandardItemResults(JsonToString.readJsonAsString(testInputStream));
        String expected = "url=https://api.open5e.com/v2/weapons/srd_battleaxe/";
        String result = sampleJsonArray.getFirst().toString().substring(1, 53);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testParseArmorClassDisplay() throws IOException {
        InputStream testInputStream = getArmorItemJsonFile();
        JSONArray sampleJsonArray = testStandardParser.parseArmorClassDisplay(JsonToString.readJsonAsString(testInputStream));
        String[] expected = listOfArmorClassDisplay();
        String[] result = new String[12];
        for (int i = 0; i < 12; i++) {
            result[i] = sampleJsonArray.get(i).toString();
        }
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testParseArmorCategory() throws IOException {
        InputStream testInputStream = getArmorItemJsonFile();
        JSONArray sampleJsonArray = testStandardParser.parseArmorCategory(JsonToString.readJsonAsString(testInputStream));
        String[] expected = {"medium", "heavy", "medium", "medium", "medium", "light", "light", "heavy", "heavy", "medium", "heavy", "light"};
        String[] result = new String[12];
        for (int i = 0; i < 12; i++) {
            result[i] = sampleJsonArray.get(i).toString();
        }
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testParseStealthDisadvantage() throws IOException {
        InputStream testInputStream = getArmorItemJsonFile();
        JSONArray sampleJsonArray = testStandardParser.parseStealthDisadvantage(JsonToString.readJsonAsString(testInputStream));
        String[] expected = {"false", "true", "false", "true", "false", "false", "true", "true", "true", "true", "true", "false"};
        String[] result = new String[12];
        for (int i = 0; i < 12; i++) {
            result[i] = sampleJsonArray.get(i).toString();
        }
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testParseStrengthScoreRequirement() throws IOException {
        InputStream testInputStream = getArmorItemJsonFile();
        JSONArray sampleJsonArray = testStandardParser.parseStrengthScoreRequirement(JsonToString.readJsonAsString(testInputStream));
        String[] expected = {null,"13",null,null,null,null,null,"15",null,null,"15",null};
        String[] result = new String[12];
        for (int i = 0; i < 12; i++) {
            if (sampleJsonArray.get(i)!=null){
                result[i] = sampleJsonArray.get(i).toString();
            }
        }
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testParseWeaponIsMartial() throws IOException {
        InputStream testInputStream = getWeaponItemJsonFile();
        JSONArray sampleJsonArray = testStandardParser.parseWeaponIsMartial(JsonToString.readJsonAsString(testInputStream));
        String[] expected = {"true","true","false","true","true"};
        String[] result = new String[5];
        for (int i = 0; i < 5; i++) {
            if (sampleJsonArray.get(i)!=null){
                result[i] = sampleJsonArray.get(i).toString();
            }
        }
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testParseWeaponProperties() throws IOException {
        InputStream testInputStream = getWeaponItemJsonFile();
        JSONArray sampleJsonArray = testStandardParser.parseWeaponProperties(JsonToString.readJsonAsString(testInputStream));
        String[] expected = {"[\"versatile (1d10)\"]","[\"ammuntion (range 25.0\\/100.0)\",\"loading\"]","[\"light\"]"};
        String[] result = new String[3];
        for (int i = 0; i < 3; i++) {
            if (sampleJsonArray.get(i)!=null){
                result[i] = sampleJsonArray.get(i).toString();
            }
        }
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testParseDamageDice() throws IOException {
        InputStream testInputStream = getWeaponItemJsonFile();
        JSONArray sampleJsonArray = testStandardParser.parseDamageDice(JsonToString.readJsonAsString(testInputStream));
        String[] expected = {"1d8","1","1d4","1d6","1d10"};
        String[] result = new String[5];
        for (int i = 0; i < 5; i++) {
            if (sampleJsonArray.get(i)!=null){
                result[i] = sampleJsonArray.get(i).toString();
            }
        }
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testParseWeaponReach() throws IOException {
        InputStream testInputStream = getWeaponItemJsonFile();
        JSONArray sampleJsonArray = testStandardParser.parseWeaponReach(JsonToString.readJsonAsString(testInputStream));
        String[] expected = {"5.0","5.0","5.0","5.0","5.0","5.0","5.0","5.0","5.0","10.0"};
        String[] result = new String[10];
        for (int i = 0; i < 10; i++) {
            if (sampleJsonArray.get(i)!=null){
                result[i] = sampleJsonArray.get(i).toString();
            }
        }
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testParseWeaponRange() throws IOException {
        InputStream testInputStream = getWeaponItemJsonFile();
        JSONArray sampleJsonArray = testStandardParser.parseWeaponRange(JsonToString.readJsonAsString(testInputStream));
        String[] expected = {"0.0","25.0","0.0","30.0","100.0"};
        String[] result = new String[5];
        for (int i = 0; i < 5; i++) {
            if (sampleJsonArray.get(i)!=null){
                result[i] = sampleJsonArray.get(i).toString();
            }
        }
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testParseWeaponLongRange() throws IOException {
        InputStream testInputStream = getWeaponItemJsonFile();
        JSONArray sampleJsonArray = testStandardParser.parseWeaponLongRange(JsonToString.readJsonAsString(testInputStream));
        String[] expected = {"0.0","100.0","0.0","120.0","400.0"};
        String[] result = new String[5];
        for (int i = 0; i < 5; i++) {
            if (sampleJsonArray.get(i)!=null){
                result[i] = sampleJsonArray.get(i).toString();
            }
        }
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testParseWeaponIsLance() throws IOException {
        InputStream testInputStream = getWeaponItemJsonFile();
        JSONArray sampleJsonArray = testStandardParser.parseWeaponIsLance(JsonToString.readJsonAsString(testInputStream));
        String[] expected = {"false","false","false","false","false","false","false","false","false","false","false","false","false","false","false","false","true"};
        String[] result = new String[17];
        for (int i = 0; i < 17; i++) {
            if (sampleJsonArray.get(i)!=null){
                result[i] = sampleJsonArray.get(i).toString();
            }
        }
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testParseWeaponIsNet() throws IOException {
        InputStream testInputStream = getWeaponItemJsonFile();
        JSONArray sampleJsonArray = testStandardParser.parseWeaponIsNet(JsonToString.readJsonAsString(testInputStream));
        String[] expected = {"false","false","false","false","false","false","false","false","false","false","false","false","false","false","false","false","false","false","false","false","false","false","false","true"};
        String[] result = new String[24];
        for (int i = 0; i < 24; i++) {
            if (sampleJsonArray.get(i)!=null){
                result[i] = sampleJsonArray.get(i).toString();
            }
        }
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testParseWeaponIsSimple() throws IOException {
        InputStream testInputStream = getWeaponItemJsonFile();
        JSONArray sampleJsonArray = testStandardParser.parseWeaponIsSimple(JsonToString.readJsonAsString(testInputStream));
        String[] expected = {"false","false","true","false","false"};
        String[] result = new String[5];
        for (int i = 0; i < 5; i++) {
            if (sampleJsonArray.get(i)!=null){
                result[i] = sampleJsonArray.get(i).toString();
            }
        }
        Assertions.assertArrayEquals(expected, result);
    }

    private InputStream getWeaponItemJsonFile() {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("SampleWeapons.json");
        assert inputStream != null;
        return inputStream;
    }

    private InputStream getArmorItemJsonFile() {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("SampleArmor.json");
        assert inputStream != null;
        return inputStream;
    }

    private String[] listOfArmorClassDisplay() {
        return new String[]{"14 + Dex modifier (max 2)", "16", "13 + Dex modifier (max 2)", "15 + Dex modifier (max 2)", "12 + Dex modifier (max 2)", "11 + Dex modifier", "11 + Dex modifier", "18", "14", "14 + Dex modifier (max 2)", "17", "12 + Dex modifier"};
    }

}