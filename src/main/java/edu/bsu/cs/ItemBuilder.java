package edu.bsu.cs;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.util.*;

public class ItemBuilder {

    MagicItemsParser attributeParser = new MagicItemsParser();
    WeaponItemParser weaponItemParser = new WeaponItemParser();
    ArmorItemParser armorItemParser = new ArmorItemParser();

    protected List<Item> generateAmountOfItems(int numberOfItemsToGenerate) throws IOException {
        List<Item> itemsList = new ArrayList<>();
        for (int i = 0; i < numberOfItemsToGenerate; i++) {
            itemsList.add(generateAny());
        }
        return itemsList;
    }

    private Item generateAny() throws IOException {
        return switch (new Random().nextInt(3)) {
            case 0 -> generateWeapon("src/main/resources/dataFiles/weapons.txt");
            case 1 -> generateArmor("src/main/resources/dataFiles/armor.txt");
            case 2 -> generateMagicItem("src/main/resources/dataFiles/magicitems.txt");
            default -> throw new IllegalStateException("Unexpected value: " + 3);
        };
    }

    protected Item generateWeapon(String filePath) throws IOException {
        JSONArray nameJsonArray =  weaponItemParser.parseWeaponItemName(JsonFileReader.readFileToString(filePath));
        int randomIndex = selectRandomItemIndex(nameJsonArray);
        Dictionary<Integer, String> statDictionary = weaponItemParser.parseAllWeaponStats(JsonFileReader.readFileToString(filePath), randomIndex);
        Item item = new Item(nameJsonArray.get(randomIndex).toString(), OutputFormatter.formatWeaponStats(statDictionary));
        item.setType("Weapon");
        return item;
    }

    protected Item generateArmor(String filePath) throws IOException {
        JSONArray nameJsonArray =  armorItemParser.parseArmorItemName(JsonFileReader.readFileToString(filePath));
        int randomIndex = selectRandomItemIndex(nameJsonArray);
        Dictionary<Integer, String> statDictionary = armorItemParser.parseAllArmorStats(JsonFileReader.readFileToString(filePath), randomIndex);
        Item item = new Item(nameJsonArray.get(randomIndex).toString(), OutputFormatter.formatArmorStats(statDictionary));
        item.setType("Armor");
        return item;
    }

    protected Item generateMagicItem(String filePath) throws IOException {
        String fileContents = JsonFileReader.readFileToString(filePath);
        String[] pageLines = fileContents.split("\n");
        int pageIndex = new Random().nextInt(pageLines.length);

        Hashtable<String, JSONArray> magicItemDetailsList = attributeParser.parseAllMagicItemDetails(fileContents, pageIndex);

        int selectedIndex = selectRandomItemIndex(magicItemDetailsList.get("name"));

        return new Item(
                magicItemDetailsList.get("name").get(selectedIndex).toString(),
                OutputFormatter.formatRarity(magicItemDetailsList.get("rarity").get(selectedIndex).toString()),
                magicItemDetailsList.get("type").get(selectedIndex).toString(),
                OutputFormatter.formatAttunement(magicItemDetailsList.get("attunement").get(selectedIndex).toString()),
                magicItemDetailsList.get("description").get(selectedIndex).toString()
                );
    }

    protected static int selectRandomItemIndex(JSONArray array){
        return new Random().nextInt(array.size());
    }

}
