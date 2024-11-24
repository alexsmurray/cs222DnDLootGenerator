package edu.bsu.cs;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class ItemListBuilder {

    private final String armorFilePath = "src/main/resources/dataFiles/armor.txt";
    private final String weaponFilePath = "src/main/resources/dataFiles/weapons.txt";
    private final String magicItemFilePath = "src/main/resources/dataFiles/magicitems.txt";
    private final ArmorItemParser armorItemParser = new ArmorItemParser(JsonFileReader.readFileToString(armorFilePath));
    private final WeaponItemParser weaponItemParser = new WeaponItemParser(JsonFileReader.readFileToString(weaponFilePath));
    private final MagicItemsParser magicItemsParser = new MagicItemsParser(JsonFileReader.readFileToString(magicItemFilePath));
    private final ItemFilter itemFilter = new ItemFilter();
    private  JSONArray nameJsonArray;

    public ItemListBuilder(List<Item> builderItemList) throws IOException {
        populateListOfItems(builderItemList);
    }

    protected void populateListOfItems(List<Item> builderItemList) throws IOException {
        populateListWithArmorItems(builderItemList);
        populateListWithWeaponItems(builderItemList);
        populateListWithMagicItems(builderItemList);
    }

    protected void populateListWithArmorItems(List<Item> builderItemList) throws IOException {
        nameJsonArray =  armorItemParser.parseArmorItemName();

        if (itemFilter.checkForItemTypeEnabled("armor")) {
            populateFilteredArmorItems(builderItemList);
        }
    }

    private void populateFilteredArmorItems(List<Item> builderItemList) {
        int armorIndexNum = 0;
        for (Object name : nameJsonArray) {
            Dictionary<Integer, String> statDictionary = armorItemParser.parseAllArmorStats(armorIndexNum);
            Item item = new Item(name.toString(), OutputFormatter.formatArmorStats(statDictionary));
            item.setType("Armor");
            builderItemList.add(item);
            armorIndexNum++;
        }
    }

    protected void populateListWithWeaponItems(List<Item> builderItemList) throws IOException {
        nameJsonArray =  weaponItemParser.parseWeaponItemName();

        if (itemFilter.checkForItemTypeEnabled("weapon")) {
            populateFilteredWeaponItems(builderItemList);
        }
    }

    private void populateFilteredWeaponItems(List<Item> builderItemList) {
        int weaponIndexNum = 0;
        for (Object name : nameJsonArray) {
            Dictionary<Integer, String> statDictionary = weaponItemParser.parseAllWeaponStats(weaponIndexNum);
            Item item = new Item(name.toString(), OutputFormatter.formatWeaponStats(statDictionary));
            item.setType("Weapon");
            builderItemList.add(item);
            weaponIndexNum++;
        }
    }

    protected void populateListWithMagicItems(List<Item> builderItemList) throws IOException {
            if (itemFilter.checkForItemTypeEnabled("magicEquipment")) {
                populateFilteredMagicItems(builderItemList);
            }
    }

    private void populateFilteredMagicItems(List<Item> builderItemList) throws IOException {
        int magicItemPages = fetchNumberOfMagicItemPages();

        for (int page = 0; page < magicItemPages; page++) {
            Hashtable<String, JSONArray> magicItemDetails = magicItemsParser.parseAllMagicItemDetails(page);
                for(int itemIndex = 0; itemIndex < magicItemDetails.get("name").size(); itemIndex++) {
                    String name = magicItemDetails.get("name").get(itemIndex).toString();
                    String rarity = OutputFormatter.formatRarity(magicItemDetails.get("rarity").get(itemIndex).toString());
                    String type = magicItemDetails.get("type").get(itemIndex).toString();
                    String attunement = OutputFormatter.formatAttunement(magicItemDetails.get("requires_attunement").get(itemIndex).toString());
                    String description = magicItemDetails.get("desc").get(itemIndex).toString();
                    Item item = new Item(name, rarity, type, attunement, description);
                    builderItemList.add(item);
                }
        }
    }

    protected int fetchNumberOfMagicItemPages() throws IOException {
        String fileContents = JsonFileReader.readFileToString(magicItemFilePath);
        String[] pageLines = fileContents.split("\n");
        return pageLines.length;
    }
}