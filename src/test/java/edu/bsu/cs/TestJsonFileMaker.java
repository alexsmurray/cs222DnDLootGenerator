package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class TestJsonFileMaker {

    @Test
    public void testWriteItemsJsonToFile() throws IOException, URISyntaxException {
        JsonFileMaker fileMaker = new JsonFileMaker();
        fileMaker.writeItemsJsonToFile("weapons");
        String fileContents = JsonFileReader.readFileToString("src/main/resources/dataFiles/weapons.txt");
        Assertions.assertFalse(fileContents.isBlank());
    }

}
