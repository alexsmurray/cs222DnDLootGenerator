package edu.bsu.cs;

import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestArmorItemParser {

    private final ArmorItemParser testStandardParser = new ArmorItemParser();
    private final InputStream armorInputStream = getArmorItemJsonFile();

    @Test
    public void testParseArmorClassDisplay() throws IOException {
        JSONArray sampleJsonArray = testStandardParser.parseArmorClassDisplay(JsonToString.readJsonAsString(armorInputStream));
        String[] expected = listOfArmorClassDisplay();
        String[] result = new String[12];
        for (int i = 0; i < 12; i++) {
            result[i] = sampleJsonArray.get(i).toString();
        }
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testParseArmorCategory() throws IOException {
        JSONArray sampleJsonArray = testStandardParser.parseArmorCategory(JsonToString.readJsonAsString(armorInputStream));
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
        JSONArray sampleJsonArray = testStandardParser.parseStrengthScoreRequirement(JsonToString.readJsonAsString(armorInputStream));
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
