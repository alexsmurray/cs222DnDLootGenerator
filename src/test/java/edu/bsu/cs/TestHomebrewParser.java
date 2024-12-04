package edu.bsu.cs;

import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Hashtable;

public class TestHomebrewParser {
    private final HomebrewItemParser homebrewItemParser;

    public TestHomebrewParser() throws IOException {
        new HomebrewFileMaker().checkForHomebrewFile();
        homebrewItemParser =  new HomebrewItemParser(JsonFileReader.readFileToString("src/main/resources/dataFiles/homebrew.txt"));
    }

    @Test
    public void testBuildJsonArrayOfHomebrewItemsNames(){
        JSONArray testArray = homebrewItemParser.buildJsonArrayOfHomebrewItemsNames();

        Assertions.assertFalse(testArray.isEmpty());
    }

    @Test
    public void testBuildJsonArrayOfHomebrewItems(){
        String testRarity = homebrewItemParser.buildJsonArrayOfHomebrewItems("Rarity").getFirst().toString();

        Assertions.assertEquals("Non-existent",testRarity);
    }

    @Test
    public void testParseHomebrewAttribute(){
        String testRarity = homebrewItemParser.parseHomebrewAttribute("Description",0);

        Assertions.assertEquals("YOU SHOULD NOT BE HERE",testRarity);
    }

    @Test
    public void testParseAllHomebrewItemDetails(){
        Hashtable<String, String> testList = homebrewItemParser.parseAllHomebrewItemDetails(0);

        Assertions.assertNull(testList.get("Name"));
    }

}