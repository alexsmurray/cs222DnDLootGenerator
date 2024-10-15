package edu.bsu.cs;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.util.Random;

public class ItemBuilder {
    protected static int selectRandomItemIndex(JSONArray array){
        return new Random().nextInt(array.size());
    }

    protected Item generateItem(String itemCategory) throws IOException {
        switch (itemCategory){
            case "Weapon":
                return generateWeapon();
            default:
                return generateAny();
        }
    }

    private Weapon generateWeapon() throws IOException {
        JSONArray nameJsonArray =  JsonParser.parseName(JsonFileReader.readFileToString("src/main/resources/weapons.txt"));
        return new Weapon(nameJsonArray.get(selectRandomItemIndex(nameJsonArray)).toString(), "Standard", "Weapon", false);
    }

    private Item generateAny() throws IOException {
        switch (new Random().nextInt(3)){
            case 0:
                return generateWeapon();
            default:
                return new Weapon("","","", false);
        }
    }

}
