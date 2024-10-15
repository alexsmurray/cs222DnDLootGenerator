package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

public class TestJsonFileMaker {

    @Test
    public void testWriteItemsJsonToFile() throws IOException, URISyntaxException {
        // We only test this method with one category to avoid 30+ API calls when testing
        JsonFileMaker fileMaker = new JsonFileMaker();
        String fileContents;
        fileMaker.writeItemsJsonToFile("weapons");
        try (FileReader fileReader = new FileReader("src/main/resources/weapons.txt")) {
            StringBuilder fileStringBuilder = new StringBuilder();
            int i = fileReader.read();
            while(i != -1) {
                fileStringBuilder.append((char)i);

                i = fileReader.read();
            }
            fileContents = fileStringBuilder.toString();
        }
        Assertions.assertFalse(fileContents.isBlank());
    }


}
