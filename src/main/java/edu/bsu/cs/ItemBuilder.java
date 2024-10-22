package edu.bsu.cs;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemBuilder {

    protected Item generateItemFromFile(String filePath) throws IOException {

        if(filePath.toLowerCase().contains("weapon")){
            return generateWeapon(filePath);
        } else if(filePath.toLowerCase().contains("armor")){
            return generateArmor(filePath);
        } else if(filePath.toLowerCase().contains("magicitem")){
            return generateMagicItem(filePath);
        }
        else return generateAny();
    }

    private Item generateWeapon(String filePath) throws IOException {
        JSONArray nameJsonArray =  JsonParser.parseStandardItemName(JsonFileReader.readFileToString(filePath));
        return new Item(nameJsonArray.get(selectRandomItemIndex(nameJsonArray)).toString(), "Standard", "Weapon", "False");
    }

    private Item generateArmor(String filePath) throws IOException {
        JSONArray nameJsonArray =  JsonParser.parseStandardItemName(JsonFileReader.readFileToString(filePath));
        return new Item(nameJsonArray.get(selectRandomItemIndex(nameJsonArray)).toString(), "Standard", "Armor", "False");
    }

    private Item generateMagicItem(String filePath) throws IOException {
        String fileContents = JsonFileReader.readFileToString(filePath);
        String[] pageLines = fileContents.split("\n");
        int pageIndex = new Random().nextInt(pageLines.length);

        JSONArray nameJsonArray =  JsonParser.parseMagicItemName(fileContents, pageIndex);
        JSONArray rarityJsonArray =  JsonParser.parseMagicItemRarity(fileContents, pageIndex);
        JSONArray typeJsonArray =  JsonParser.parseMagicItemType(fileContents, pageIndex);
        JSONArray attunementJsonArray =  JsonParser.parseMagicItemAttunement(fileContents, pageIndex);

        int selectedIndex = selectRandomItemIndex(nameJsonArray);

        return new Item(
                nameJsonArray.get(selectedIndex).toString(),
                OutputFormatter.formatRarity(rarityJsonArray.get(selectedIndex).toString()),
                typeJsonArray.get(selectedIndex).toString(),
                OutputFormatter.formatAttunement(attunementJsonArray.get(selectedIndex).toString()));
    }

    private Item generateAny() throws IOException {
        return switch (new Random().nextInt(3)) {
            case 0 -> generateWeapon("src/main/resources/weapons.txt");
            case 1 -> generateArmor("src/main/resources/armor.txt");
            case 2 -> generateMagicItem("src/main/resources/magicitems.txt");
            default -> throw new IllegalStateException("Unexpected value: " + 3);
        };
    }

    protected static int selectRandomItemIndex(JSONArray array){
        return new Random().nextInt(array.size());
    }

    protected List<Item> generateAmountOfItems() throws IOException {
        List<Item> itemsList = new ArrayList<>();
        for (int i = 0; i < Configuration.getNumItemsRequested(); i++) {
            itemsList.add(generateAny());
        }
        return itemsList;
    }

}//
