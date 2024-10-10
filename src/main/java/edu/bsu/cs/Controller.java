package edu.bsu.cs;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLConnection;

public class Controller {
    protected static String fetchStringifiedJson(String input) throws IOException, URISyntaxException {
        URLConnection connection= APIConnection.connectToAPI(input);
        return JsonToString.readJsonAsString(connection.getInputStream());
    }
}
