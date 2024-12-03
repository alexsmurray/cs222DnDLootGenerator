package edu.bsu.cs;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonFileMaker {

    NextPageParser nextPageParser = new NextPageParser();

    protected static void updateAPIFiles() throws IOException, URISyntaxException {
        Files.createDirectories(Paths.get("src/main/resources/dataFiles"));
        JsonFileMaker jsonFileMaker = new JsonFileMaker();
        boolean updated = false;
        while (!updated) {
            jsonFileMaker.writeItemsJsonToFile("magicitems");
            jsonFileMaker.writeItemsJsonToFile("armor");
            jsonFileMaker.writeItemsJsonToFile("weapons");
            checkForHomebrewFile();
            updated = true;
        }
    }

    protected static void checkForHomebrewFile() throws IOException {
        Files.createDirectories(Paths.get("src/main/resources/dataFiles"));
        Path homebrew = Paths.get("src/main/resources/dataFiles/homebrew.txt");
        if (!Files.exists(homebrew)){
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

        homebrewFile.write(fileContents.substring(0, fileContents.length() - 3) +"\n" + itemDetails);

        homebrewFile.close();

    }

    protected void writeItemsJsonToFile(String categoryName) throws  IOException, URISyntaxException {
        String version = "v2";
        if (categoryName.equals("magicitems")) {version = "v1";}

        int pageNum = 1;
        FileWriter itemsApi = new FileWriter("src/main/resources/dataFiles/" + categoryName + ".txt");
        InputStream inputStream = APIConnection.fetchConnectionPath(version + "/" + categoryName + "/?format=json&page=" + pageNum).getInputStream();
        String inputStreamString = JsonToString.readJsonAsString(inputStream);

        String nextPage = nextPageParser.parseNext(inputStreamString);
        StringBuilder itemsString = new StringBuilder();
        while (!nextPage.equals("[null]")) {
            itemsString.append(inputStreamString).append("\n");
            pageNum ++;
            inputStream = APIConnection.fetchConnectionPath("v1/magicitems/?format=json&page="+pageNum).getInputStream();
            inputStreamString = JsonToString.readJsonAsString(inputStream);
            nextPage = nextPageParser.parseNext(inputStreamString);
        }
        itemsString.append(inputStreamString);
        itemsApi.write(itemsString.toString());
        itemsApi.close();
    }

}
