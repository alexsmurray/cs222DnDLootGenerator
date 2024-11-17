package edu.bsu.cs;

import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Dictionary;

public class TestWeaponItemParser {

    private final WeaponItemParser testWeaponParser = new WeaponItemParser(JsonFileReader.readFileToString("src/test/resources/SampleWeapons.json"));

    public TestWeaponItemParser() throws IOException {
    }

    @Test
    public void testParseAllWeaponStats() {
        Dictionary<Integer, String> testStatDictionary = testWeaponParser.parseAllWeaponStats(0);
        String[] result = buildStatArray(testStatDictionary);
        String[] expected = {"true", "false", "1d8", "5.0", "slashing"};
        Assertions.assertArrayEquals(expected, result);
    }

    private String[] buildStatArray(Dictionary<Integer, String> testStatDictionary) {
        String[] testStatArray = new String[5];
        for (int i = 1; i <= 5; i++) {
            testStatArray[i - 1] = testStatDictionary.get(i);
        }
        return testStatArray;
    }

    @Test
    public void testGetStandardItemNameFromSampleJson() {
        JSONArray sampleJsonArray = testWeaponParser.parseWeaponItemName();
        String[] expected = {"Battleaxe", "Blowgun", "Club", "Crossbow, hand", "Crossbow, heavy"};
        String[] result = new String[5];
        for (int i = 0; i < 5; i++) {
            result[i] = sampleJsonArray.get(i).toString();
        }
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testParseWeaponIsMartial() {
        JSONArray sampleJsonArray = testWeaponParser.parseWeaponIsMartial();
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
    public void testParseWeaponProperties() {
        JSONArray sampleJsonArray = testWeaponParser.parseWeaponProperties();
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
    public void testParseDamageDice() {
        JSONArray sampleJsonArray = testWeaponParser.parseDamageDice();
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
    public void testParseWeaponReach() {
        JSONArray sampleJsonArray = testWeaponParser.parseWeaponReach();
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
    public void testParseWeaponIsLance() {
        JSONArray sampleJsonArray = testWeaponParser.parseWeaponIsLance();
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
    public void testParseWeaponIsNet() {
        JSONArray sampleJsonArray = testWeaponParser.parseWeaponIsNet();
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
    public void testParseWeaponIsSimple() {
        JSONArray sampleJsonArray = testWeaponParser.parseWeaponIsSimple();
        String[] expected = {"false","false","true","false","false"};
        String[] result = new String[5];
        for (int i = 0; i < 5; i++) {
            if (sampleJsonArray.get(i)!=null){
                result[i] = sampleJsonArray.get(i).toString();
            }
        }
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testParseWeaponDamageType() {
        JSONArray sampleJsonArray = testWeaponParser.parseWeaponDamageType();
        String expected = OutputFormatter.formatWeaponDamageType(sampleJsonArray.getFirst()).toString();
        String result = "slashing";
        Assertions.assertEquals(expected, result);
    }

}