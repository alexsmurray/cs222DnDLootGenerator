package edu.bsu.cs;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLConnection;


public class Weapon extends Item {
    static Weapon wp = new Weapon();
    public static void main(String[] args) throws URISyntaxException, IOException {
        int numOfItemsChosen= 4;
        for (int i = 0; i < numOfItemsChosen; i++) {
            System.out.println(wp.getName("v2/weapons/?format=json"));
        }
    }

    public String getName(String input) throws URISyntaxException, IOException {
        URLConnection connection= APIConnection.connectToAPI(input);
        String data = JsonToString.readJsonAsString(connection.getInputStream());
        return InputFormatter.shuffleJsonArray(JsonParser.getNameFromJsonSample(data)).getFirst().toString();
    }

    //Get as many names as we want
    //shuffle names
    //Search details with name
}

