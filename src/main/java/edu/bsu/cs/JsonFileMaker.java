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
            new HomebrewFileMaker().checkForHomebrewFile();
            updated = true;
        }
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
