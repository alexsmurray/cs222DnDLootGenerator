package edu.bsu.cs;

import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Dictionary;

public class TestArmorItemParser {

    private final ArmorItemParser testArmorParser = new ArmorItemParser();
    private final InputStream armorInputStream = getArmorItemJsonFile();

    @Test
    public void testParseAllArmorStats() throws IOException {
        String stringifiedJson = JsonFileReader.readFileToString("src/test/resources/SampleArmor.json");
        Dictionary<Integer, String> testStatDictionary = testArmorParser.parseAllArmorStats(stringifiedJson, 0);
        String[] result = buildStatArray(testStatDictionary);
        String[] expected = {"14 + Dex modifier (max 2)", "medium", "false", "None"};
        Assertions.assertArrayEquals(expected, result);
    }

    private String[] buildStatArray(Dictionary<Integer, String> testStatDictionary) {
        String[] testStatArray = new String[4];
        for (int i = 1; i <= 4; i++) {
            testStatArray[i-1] = testStatDictionary.get(i);
        }
        return testStatArray;
    }

    @Test
    public void testParseArmorClassDisplay() throws IOException {
        JSONArray sampleJsonArray = testArmorParser.parseArmorClassDisplay(JsonToString.readJsonAsString(armorInputStream));
        String[] expected = listOfArmorClassDisplay();
        String[] result = new String[12];
        for (int i = 0; i < 12; i++) {
            result[i] = sampleJsonArray.get(i).toString();
        }
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testParseArmorCategory() throws IOException {
        JSONArray sampleJsonArray = testArmorParser.parseArmorCategory(JsonToString.readJsonAsString(armorInputStream));
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
        JSONArray sampleJsonArray = testArmorParser.parseStealthDisadvantage(JsonToString.readJsonAsString(testInputStream));
        String[] expected = {"false", "true", "false", "true", "false", "false", "true", "true", "true", "true", "true", "false"};
        String[] result = new String[12];
        for (int i = 0; i < 12; i++) {
            result[i] = sampleJsonArray.get(i).toString();
        }
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testParseStrengthScoreRequirement() throws IOException {
        JSONArray sampleJsonArray = testArmorParser.parseStrengthScoreRequirement(JsonToString.readJsonAsString(armorInputStream));
        String[] expected = {null,"13",null,null,null,null,null,"15",null,null,"15",null};
        String[] result = new String[12];
        for (int i = 0; i < 12; i++) {
            if (sampleJsonArray.get(i)!=null){
                result[i] = sampleJsonArray.get(i).toString();
            }
        }
        Assertions.assertArrayEquals(expected, result);
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
