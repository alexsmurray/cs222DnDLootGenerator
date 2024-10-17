package edu.bsu.cs;

import java.io.IOException;
import java.net.*;

public class APIConnection {

    public static URLConnection fetchConnectionPath(String userInput) throws URISyntaxException, IOException {
        if (ErrorHandler.verifyNetworkConnection().equals("Connected")){
            URL url = new URI("https://api.open5e.com/" + userInput).toURL();
            return url.openConnection();
        }else {
            GUI.displayNetworkAlert();
        }
       return null;
    }



}
