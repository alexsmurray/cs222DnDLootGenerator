package edu.bsu.cs;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.net.URISyntaxException;


public class Weapon extends Item {

    public String getName(String input) throws URISyntaxException, IOException {
        JSONArray nameJsonArray =  JsonParser.parseName(Controller.fetchStringifiedJson(input));
        return InputFormatter.shuffleJsonArray(nameJsonArray).getFirst().toString();
    }

    //Get as many names as we want
    //shuffle names
    //Search details with name
}

