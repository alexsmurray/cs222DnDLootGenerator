package edu.bsu.cs;

import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestWeaponItemParser {

    private final WeaponItemParser testStandardParser = new WeaponItemParser();
    private final InputStream weaponInputStream = getWeaponItemJsonFile();

    @Test
    public void testGetStandardItemNameFromSampleJson() throws IOException {
        JSONArray sampleJsonArray = testStandardParser.parseStandardItemName(JsonToString.readJsonAsString(weaponInputStream));
        String[] expected = {"Battleaxe", "Blowgun", "Club", "Crossbow, hand", "Crossbow, heavy"};
        String[] result = new String[5];
        for (int i = 0; i < 5; i++) {
            result[i] = sampleJsonArray.get(i).toString();
        }
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testParseWeaponIsMartial() throws IOException {
        JSONArray sampleJsonArray = testStandardParser.parseWeaponIsMartial(JsonToString.readJsonAsString(weaponInputStream));
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
        JSONArray sampleJsonArray = testStandardParser.parseWeaponProperties(JsonToString.readJsonAsString(weaponInputStream));
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
        JSONArray sampleJsonArray = testStandardParser.parseWeaponRange(JsonToString.readJsonAsString(weaponInputStream));
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
        JSONArray sampleJsonArray = testStandardParser.parseWeaponIsLance(JsonToString.readJsonAsString(weaponInputStream));
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
        JSONArray sampleJsonArray = testStandardParser.parseWeaponIsNet(JsonToString.readJsonAsString(weaponInputStream));
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

    @Test
    public void testParseWeaponDamageType() throws IOException {
        JSONArray sampleJsonArray = testStandardParser.parseWeaponDamageType(JsonToString.readJsonAsString(weaponInputStream));
        String expected = OutputFormatter.formatWeaponDamageType(sampleJsonArray.getFirst()).toString();
        String result = "slashing";
        Assertions.assertEquals(expected, result);
    }

    private InputStream getWeaponItemJsonFile() {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("SampleWeapons.json");
        assert inputStream != null;
        return inputStream;
    }

}