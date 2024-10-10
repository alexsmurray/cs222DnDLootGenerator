package edu.bsu.cs;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;

public class StandardItemBuilder {
    protected static int selectRandomItemIndex(JSONArray array){
        return new Random().nextInt(array.size());
    }

    protected Weapon generateWeapon() throws IOException, URISyntaxException {
        JSONArray nameJsonArray =  JsonParser.parseName(Controller.fetchStringifiedJson("v2/weapons"));
        return new Weapon(nameJsonArray.get(selectRandomItemIndex(nameJsonArray)).toString());
    }
}
