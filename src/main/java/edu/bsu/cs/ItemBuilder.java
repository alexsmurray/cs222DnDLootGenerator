package edu.bsu.cs;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.util.*;

public class ItemBuilder {

    String armorFilePath = "src/main/resources/dataFiles/armor.txt";
    String weaponsFilePath = "src/main/resources/dataFiles/weapons.txt";
    String magicItemFilePath = "src/main/resources/dataFiles/magicitems.txt";

    protected List<Item> generateAmountOfItems(int numberOfItemsToGenerate) throws IOException {
        List<Item> itemsList = new ArrayList<>();
        return determineToIncludeMundaneItems(numberOfItemsToGenerate, itemsList);
    }

    private List<Item> determineToIncludeMundaneItems(int numberOfItemsToGenerate, List<Item> itemsList) throws IOException {
        if (includeMundaneItems()) {
            return generateListIncludingMundaneItems(numberOfItemsToGenerate, itemsList);
        } else {
            return generateListOfMagicItems(numberOfItemsToGenerate, itemsList);
        }
    }

    private boolean includeMundaneItems() throws IOException {
        String[] configurationValues = new ConfigurationFileReader().readConfigFileAsString().split(",");
        return Double.parseDouble(configurationValues[0]) == 0;
    }

    private List<Item> generateListIncludingMundaneItems(int numberOfItemsToGenerate, List<Item> itemsList) throws IOException {
        for (int i = 0; i < numberOfItemsToGenerate; i++) {
            itemsList.add(generateAny());
        }
        return itemsList;
    }

    private List<Item> generateListOfMagicItems(int numberOfItemsToGenerate, List<Item> itemsList) throws IOException {
        for (int i = 0; i < numberOfItemsToGenerate; i++) {
            itemsList.add(generateMagicItem(magicItemFilePath));
        }
        return itemsList;
    }

    private Item generateAny() throws IOException {
        return switch (new Random().nextInt(3)) {
            case 0 -> generateWeapon(weaponsFilePath);
            case 1 -> generateArmor(armorFilePath);
            case 2 -> generateMagicItem(magicItemFilePath);
            default -> throw new IllegalStateException("Unexpected value: " + 3);
        };
    }

    protected Item generateWeapon(String filePath) throws IOException {
        WeaponItemParser weaponItemParser = new WeaponItemParser(JsonFileReader.readFileToString(filePath));
        JSONArray nameJsonArray =  weaponItemParser.parseWeaponItemName();
        int randomIndex = selectRandomItemIndex(nameJsonArray);


        Dictionary<Integer, String> statDictionary = weaponItemParser.parseAllWeaponStats(randomIndex);
        Item item = new Item(nameJsonArray.get(randomIndex).toString(), OutputFormatter.formatWeaponStats(statDictionary));
        item.setType("Weapon");

        return item;
    }

    protected Item generateArmor(String filePath) throws IOException {
        ArmorItemParser armorItemParser = new ArmorItemParser(JsonFileReader.readFileToString(filePath));
        JSONArray nameJsonArray =  armorItemParser.parseArmorItemName();
        int randomIndex = selectRandomItemIndex(nameJsonArray);
        Dictionary<Integer, String> statDictionary = armorItemParser.parseAllArmorStats(randomIndex);
        Item item = new Item(nameJsonArray.get(randomIndex).toString(), OutputFormatter.formatArmorStats(statDictionary));
        item.setType("Armor");
        return item;
    }

    protected Item generateMagicItem(String filePath) throws IOException {
        String fileContents = JsonFileReader.readFileToString(filePath);

        String[] pageLines = fileContents.split("\n");
        int pageIndex = new Random().nextInt(pageLines.length);

        MagicItemsParser attributeParser = new MagicItemsParser(fileContents);
        Hashtable<String, JSONArray> magicItemDetailsList = attributeParser.parseAllMagicItemDetails(pageIndex);

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
