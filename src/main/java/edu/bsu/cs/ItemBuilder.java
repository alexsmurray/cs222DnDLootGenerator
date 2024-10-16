package edu.bsu.cs;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemBuilder {
    protected static int selectRandomItemIndex(JSONArray array){
        return new Random().nextInt(array.size());
    }

    protected Item generateItem(String itemCategory) throws IOException {
        if(ErrorHandler.verifyAllItemFilesExist()) {
                return switch (itemCategory) {
                    case "Weapon" -> generateWeapon();
                    case "Armor" -> generateArmor();
                    case "Magic" -> generateMagicItem();
                    default -> generateAny();
                };
        }
        throw new AssertionError("You shouldn't be here.");
    }

    private Weapon generateWeapon() throws IOException {
        JSONArray nameJsonArray =  JsonParser.parseName(JsonFileReader.readFileToString("src/main/resources/weapons.txt"));
        return new Weapon(nameJsonArray.get(selectRandomItemIndex(nameJsonArray)).toString(), "Standard", "Weapon", "False");
    }

    private Armor generateArmor() throws IOException {
        JSONArray nameJsonArray =  JsonParser.parseName(JsonFileReader.readFileToString("src/main/resources/armor.txt"));
        return new Armor(nameJsonArray.get(selectRandomItemIndex(nameJsonArray)).toString(), "Standard", "Armor", "False");
    }

    private MagicItem generateMagicItem() throws IOException {
        String fileContents = JsonFileReader.readFileToString("src/main/resources/magicitems.txt");
        JSONArray nameJsonArray =  JsonParser.parseName(fileContents);
        JSONArray rarityJsonArray =  JsonParser.parseRarity(fileContents);
        JSONArray typeJsonArray =  JsonParser.parseType(fileContents);
        JSONArray attunementJsonArray =  JsonParser.parseAttunement(fileContents);

        int selectedIndex = selectRandomItemIndex(nameJsonArray);
        return new MagicItem(
                nameJsonArray.get(selectedIndex).toString(),
                rarityJsonArray.get(selectedIndex).toString(),
                typeJsonArray.get(selectedIndex).toString(),
                OutputFormatter.formatAttunement(attunementJsonArray.get(selectedIndex).toString()));
    }

    private Item generateAny() throws IOException {
        return switch (new Random().nextInt(3)) {
            case 0 -> generateWeapon();
            case 1 -> generateArmor();
            case 2 -> generateMagicItem();
            default -> throw new IllegalStateException("Unexpected value: " + 3);
        };
    }

    protected List<Item> generateAmountOfItems() throws IOException {
        List<Item> itemsList = new ArrayList<>();
        for (int i = 0; i < Configuration.getNumItemsRequested(); i++) {
            itemsList.add(generateItem("Any"));
        }
        return itemsList;
    }
}
