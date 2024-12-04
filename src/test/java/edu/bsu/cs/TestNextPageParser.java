package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestNextPageParser {

    @Test
    public void testParseNext() throws IOException {
        InputStream testInputStream = getMagicItemJsonFile();
        NextPageParser pageParser = new NextPageParser();
        String result = pageParser.parseNext(JsonToString.readJsonAsString(testInputStream));
        String expected = "format=json&page=2";
        Assertions.assertEquals(expected, result.substring(45, 63));
    }

    private InputStream getMagicItemJsonFile() {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("SampleMagicItem.json");
        assert inputStream != null;
        return inputStream;
    }

}
