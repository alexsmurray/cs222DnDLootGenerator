package edu.bsu.cs;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.util.Random;

public class ItemBuilder {
    protected static int selectRandomItemIndex(JSONArray array){
        return new Random().nextInt(array.size());
    }

    protected Weapon generateWeapon() throws IOException {
        JSONArray nameJsonArray =  JsonParser.parseName(JsonFileReader.readFileToString("src/main/resources/weapons.txt"));
        return new Weapon(nameJsonArray.get(selectRandomItemIndex(nameJsonArray)).toString(), "Standard", "Weapon", false);
    }
}
