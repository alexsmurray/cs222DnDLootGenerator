package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestJsonFileMaker {

    @Test
    public void testWriteItemsJsonToFile() throws IOException, URISyntaxException {
        Files.createDirectories(Paths.get("src/main/resources/dataFiles"));

        JsonFileMaker fileMaker = new JsonFileMaker();
        fileMaker.writeItemsJsonToFile("weapons");
        String fileContents = JsonFileReader.readFileToString("src/main/resources/dataFiles/weapons.txt");
        Assertions.assertFalse(fileContents.isBlank());
    }

}
