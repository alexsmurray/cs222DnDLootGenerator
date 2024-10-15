package edu.bsu.cs;

import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class TestParseJson {
    @Test
    public void testGetNameFromSampleJson() throws IOException {
        InputStream testInputStream = getJsonFile();
        JSONArray sampleJsonArray = JsonParser.parseName(JsonToString.readJsonAsString(testInputStream));
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
        JSONArray sampleJsonArray = JsonParser.parseRarity(JsonToString.readJsonAsString(testInputStream));
        String[] expected = {"Rare","Very Rare","Uncommon","Uncommon","Very Rare"};
        String[] result = new String[5];
        for (int i = 0; i < 5; i++) {
            result[i] = InputFormatter.formatRarity(sampleJsonArray.get(i).toString());
        }
        Assertions.assertArrayEquals(expected,result);
    }

    @Test
    public void testGetAttunementFromSampleJson() throws IOException {
        InputStream testInputStream = getJsonFile();
        JSONArray sampleJsonArray = JsonParser.parseAttunement(JsonToString.readJsonAsString(testInputStream));
        String[] expected = {"False","False","True","False","True"};
        String [] result = new String[5];
        for (int i = 0; i < 5; i++) {
            result[i] =InputFormatter.formatAttunement(sampleJsonArray.get(i).toString());
        }
        Assertions.assertArrayEquals(expected,result);
    }

    @Test
    public void testMagicItemsTextFile() throws IOException, URISyntaxException {
        JsonFileMaker jsonFileMaker = new JsonFileMaker();
        jsonFileMaker.writeMagicItemsJsonToFile();
        FileInputStream magicItemInputStream = getItemsFile("magicitems");
        String stringifiedJson = JsonToString.readJsonAsString(magicItemInputStream);
        JSONArray sampleJsonArray = JsonParser.parseName(stringifiedJson);
        Assertions.assertNotEquals(sampleJsonArray.size(), 0);
    }
    @Test
    public void testArmorTextFile() throws IOException, URISyntaxException {
        JsonFileMaker jsonFileMaker = new JsonFileMaker();
        jsonFileMaker.writeArmorJsonToFile();
        FileInputStream armorInputStream = getItemsFile("armor");
        String stringifiedJson = JsonToString.readJsonAsString(armorInputStream);
        JSONArray sampleJsonArray = JsonParser.parseName(stringifiedJson);
        Assertions.assertNotEquals(sampleJsonArray.size(), 0);
    }
    @Test
    public void testWeaponsTextFile() throws IOException, URISyntaxException {
        JsonFileMaker jsonFileMaker = new JsonFileMaker();
        jsonFileMaker.writeWeaponsJsonToFile();
        FileInputStream weaponsInputStream = getItemsFile("weapons");
        String stringifiedJson = JsonToString.readJsonAsString(weaponsInputStream);
        JSONArray sampleJsonArray = JsonParser.parseName(stringifiedJson);
        Assertions.assertNotEquals(sampleJsonArray.size(), 0);
    }

    private InputStream getJsonFile(){
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("SampleMagicItemPage1.json");
        assert inputStream != null;
        return inputStream;
    }
    private FileInputStream getItemsFile(String fileName) throws FileNotFoundException {
        return new FileInputStream("src/main/resources/" + fileName + ".txt");
    }

}
