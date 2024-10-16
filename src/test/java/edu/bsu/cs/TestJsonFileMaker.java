package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class TestJsonFileMaker {

    @Test
    public void testWriteItemsJsonToFile() throws IOException, URISyntaxException {
        // We only test this method with one category to avoid 30+ API calls when testing
        JsonFileMaker fileMaker = new JsonFileMaker();
        fileMaker.writeItemsJsonToFile("weapons");
        String fileContents = JsonFileReader.readFileToString("src/main/resources/weapons.txt");
        Assertions.assertFalse(fileContents.isBlank());
    }


}
