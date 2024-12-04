package edu.bsu.cs;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HomebrewFileMaker {

    protected void checkForHomebrewFile() throws IOException {
        Files.createDirectories(Paths.get("src/main/resources/dataFiles"));
        Path homebrew = Paths.get("src/main/resources/dataFiles/homebrew.txt");
        if (!Files.exists(homebrew)) {
            FileWriter homebrewFile = new FileWriter("src/main/resources/dataFiles/homebrew.txt");
            homebrewFile.write("""
                    {
                    "results": [{
                    \t"Item_Type": "Homebrew Item",
                    \t"Name": null,
                    \t"Rarity": "Non-existent",
                    \t"Attunement": false,
                    \t"Description": "YOU SHOULD NOT BE HERE",
                    },]
                    }""");
            homebrewFile.close();
        }
    }

    protected void writeHomebrewToFile(String itemDetails) throws IOException {
        checkForHomebrewFile();
        String fileContents = JsonFileReader.readFileToString("src/main/resources/dataFiles/homebrew.txt");
        FileWriter homebrewFile = new FileWriter("src/main/resources/dataFiles/homebrew.txt");

        homebrewFile.write(fileContents.substring(0, fileContents.length() - 3) + "\n" + itemDetails);

        homebrewFile.close();
    }

}
