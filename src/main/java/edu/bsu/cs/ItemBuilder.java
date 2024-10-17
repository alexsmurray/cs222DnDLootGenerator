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
        JSONArray nameJsonArray =  JsonParser.parseStandardItemName(JsonFileReader.readFileToString("src/main/resources/weapons.txt"));
        return new Weapon(nameJsonArray.get(selectRandomItemIndex(nameJsonArray)).toString(), "Standard", "Weapon", "False");
    }

    private Armor generateArmor() throws IOException {
        JSONArray nameJsonArray =  JsonParser.parseStandardItemName(JsonFileReader.readFileToString("src/main/resources/armor.txt"));
        return new Armor(nameJsonArray.get(selectRandomItemIndex(nameJsonArray)).toString(), "Standard", "Armor", "False");
    }

    private MagicItem generateMagicItem() throws IOException {
        String fileContents = JsonFileReader.readFileToString("src/main/resources/magicitems.txt");
        String[] pageLines = fileContents.split("\n");
        int pageIndex = new Random().nextInt(pageLines.length);

        JSONArray nameJsonArray =  JsonParser.parseMagicItemName(fileContents, pageIndex);
        JSONArray rarityJsonArray =  JsonParser.parseMagicItemRarity(fileContents, pageIndex);
        JSONArray typeJsonArray =  JsonParser.parseMagicItemType(fileContents, pageIndex);
        JSONArray attunementJsonArray =  JsonParser.parseMagicItemAttunement(fileContents, pageIndex);

        int selectedIndex = selectRandomItemIndex(nameJsonArray);

        System.out.println("Name: " + nameJsonArray.getFirst());
        System.out.println("Rarity: " + rarityJsonArray.getFirst());
        System.out.println("Type: " + typeJsonArray.getFirst());
        System.out.println("Attunement: " + attunementJsonArray.getFirst());
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
