package edu.bsu.cs;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class JsonFileMaker {

    protected void writeItemsJsonToFile(String categoryName) throws  IOException, URISyntaxException {
        String version = "v2";
        if (categoryName.equals("magicitems")) {version = "v1";}

        int pageNum = 1;
        FileWriter itemsApi = new FileWriter("src/main/resources/" + categoryName + ".txt");
        InputStream inputStream = APIConnection.fetchConnectionPath(version + "/" + categoryName + "/?format=json&page=" + pageNum).getInputStream();
        String inputStreamString = JsonToString.readJsonAsString(inputStream);

        String nextPage = JsonParser.parseNext(inputStreamString);
        StringBuilder itemsString = new StringBuilder();
        while (!nextPage.equals("[null]")) {
            itemsString.append(inputStreamString).append("\n");
            pageNum ++;
            inputStream = APIConnection.fetchConnectionPath("v1/magicitems/?format=json&page="+pageNum).getInputStream();
            inputStreamString = JsonToString.readJsonAsString(inputStream);
            nextPage = JsonParser.parseNext(inputStreamString);
        }
        itemsString.append(inputStreamString);
        itemsApi.write(itemsString.toString());
        itemsApi.close();
    }

    protected static String checkForFileUpdate(){
        EventHandler eventHandler = new EventHandler();
        if (!ErrorHandler.verifyNetworkConnection().equals("Network Error")){
            try {
                eventHandler.updateAPIFiles();
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
            return "";
        }else {
            return "Network Error";
        }
    }

}
