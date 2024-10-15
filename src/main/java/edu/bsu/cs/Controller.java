package edu.bsu.cs;

import java.io.IOException;
import java.net.URISyntaxException;

public class Controller {

    protected void updateAPIFiles() throws IOException, URISyntaxException {
        JsonFileMaker jsonFileMaker = new JsonFileMaker();
        boolean updated = false;
        while (!updated) {
            jsonFileMaker.writeItemsJsonToFile("magicitems");
            jsonFileMaker.writeItemsJsonToFile("armor");
            jsonFileMaker.writeItemsJsonToFile("weapons");
            updated = true;
        }
    }
}
