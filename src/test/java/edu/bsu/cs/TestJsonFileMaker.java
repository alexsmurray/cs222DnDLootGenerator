package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
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

    @Test
    public void testWriteItemsToHomebrewFile() throws IOException{
        JsonFileMaker fileMaker = new JsonFileMaker();
        JsonFileMaker.checkForHomebrewFile();
        String fileContents;

        File homebrew = new File("src/main/resources/dataFiles/homebrew.txt");
        if (homebrew.length()==0){
            fileMaker.writeHomebrewToFile( "Item Type: TestItem");
        }
        fileContents = JsonFileReader.readFileToString("src/main/resources/dataFiles/homebrew.txt");

        Assertions.assertFalse(fileContents.isBlank());
        clearFile();
    }


    private void clearFile() throws IOException {
        String fileContents = JsonFileReader.readFileToString("src/main/resources/dataFiles/homebrew.txt");
        FileWriter fileWriter = new FileWriter("src/main/resources/dataFiles/homebrew.txt");
        if (fileContents.contains("TestItem")){
            fileWriter.write("");
        }
        fileWriter.close();
    }

}
