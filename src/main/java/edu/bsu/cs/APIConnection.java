package edu.bsu.cs;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

public class APIConnection {

    protected static URLConnection fetchConnectionPath(String userInput) throws URISyntaxException, IOException {
        URL url = new URI("https://api.open5e.com/" + userInput).toURL();
        return url.openConnection();
    }

}
