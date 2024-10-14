package edu.bsu.cs;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLConnection;

public class Controller {
    protected static String fetchStringifiedJson(String input) throws IOException, URISyntaxException {
        URLConnection connection= APIConnection.fetchConnectionPath(input);
        return JsonToString.readJsonAsString(connection.getInputStream());
    }

    protected void updateAPIFiles() throws IOException, URISyntaxException {
        JsonFileMaker jsonFileMaker = new JsonFileMaker();
        boolean updated = false;
        while (!updated) {
            jsonFileMaker.writeArmorJsonToFile();
            jsonFileMaker.writeMagicItemsJsonToFile();
            jsonFileMaker.writeWeaponsJsonToFile();
            updated = true;
        }
    }
}
