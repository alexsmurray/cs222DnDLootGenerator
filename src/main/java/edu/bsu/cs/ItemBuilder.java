package edu.bsu.cs;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.util.Random;

public class ItemBuilder {
    protected static int selectRandomItemIndex(JSONArray array){
        return new Random().nextInt(array.size());
    }
    protected static Item nullItem;

    protected Item generateItem(String itemCategory) throws IOException {
        if(ErrorHandler.verifyAllItemFilesExist()) {
            return switch (itemCategory) {
                case "Weapon" -> generateWeapon();
                case "Armor" -> generateArmor();
                default -> generateAny();
            };
        } else {
            return nullItem;
        }
    }

    private Weapon generateWeapon() throws IOException {
        JSONArray nameJsonArray =  JsonParser.parseName(JsonFileReader.readFileToString("src/main/resources/weapons.txt"));
        return new Weapon(nameJsonArray.get(selectRandomItemIndex(nameJsonArray)).toString(), "Standard", "Weapon", false);
    }

    private Armor generateArmor() throws IOException {
        JSONArray nameJsonArray =  JsonParser.parseName(JsonFileReader.readFileToString("src/main/resources/armor.txt"));
        return new Armor(nameJsonArray.get(selectRandomItemIndex(nameJsonArray)).toString(), "Standard", "Weapon", false);
    }

    private Item generateAny() throws IOException {
        return switch (new Random().nextInt(3)) {
            case 0 -> generateWeapon();
            case 1 -> generateArmor();
            default -> nullItem;
        };
    }

}
