package edu.bsu.cs;

import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestJsonArrayBuilder {

    @Test
    public void testBuildJsonArrayOfMagicItemPages() throws IOException {
        JsonArrayBuilder testJsonArrayBuilder = new JsonArrayBuilder();
        String stringifiedJson = JsonFileReader.readFileToString("src/test/resources/SampleMagicItem.json");
        JSONArray testJsonArray = testJsonArrayBuilder.buildJsonArrayOfMagicItemPages(stringifiedJson, "name");
        JSONArray firstPage = (JSONArray) testJsonArray.getFirst();
        Assertions.assertEquals(firstPage.getFirst(), "Aberrant Agreement");
    }

}
