package edu.bsu.cs;

import net.minidev.json.JSONArray;

import java.io.IOException;
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

    public ItemListBuilder(List<Item> itemsList) throws IOException {
        populateListOfItems(itemsList);
    }

    protected void populateListOfItems(List<Item> filterItemList) throws IOException {
        populateListWithArmorItems(filterItemList);
        populateListWithWeaponItems(filterItemList);
        populateListWithMagicItems(filterItemList);
    }

    protected void populateListWithArmorItems(List<Item> filterItemList) throws IOException {
        nameJsonArray =  armorItemParser.parseArmorItemName();

        if (itemFilter.checkForItemTypeEnabled("armor")) {
            populateFilteredArmorItems(filterItemList);
        }
    }

    private void populateFilteredArmorItems(List<Item> filterItemList) {
        //TODO:: Grab all the info for armor
        for (Object name : nameJsonArray) {
            Item item = new Item(name.toString(), "rarity", "type", "attunement", "details");
            filterItemList.add(item);
        }
    }

    protected void populateListWithWeaponItems(List<Item> filterItemList) throws IOException {
        nameJsonArray =  weaponItemParser.parseWeaponItemName();

        if (itemFilter.checkForItemTypeEnabled("weapon")) {
            populateFilteredWeaponItems(filterItemList);
        }
    }

    private void populateFilteredWeaponItems(List<Item> filterItemList) {
        //TODO:: Grab all the info for weapons
        for (Object name : nameJsonArray) {
            Item item = new Item(name.toString(), "rarity", "type", "attunement", "details");
            filterItemList.add(item);
        }
    }

    protected void populateListWithMagicItems(List<Item> filterItemList) throws IOException {
        int magicItemPages = fetchNumberOfMagicItemPages();
        for (int page = 0; page < magicItemPages; page++) {
            nameJsonArray = magicItemsParser.parseMagicItemName(page);
            if (itemFilter.checkForItemTypeEnabled("magicEquipment")) {
                populateFilteredMagicItems(filterItemList);
            }
        }
    }

    protected int fetchNumberOfMagicItemPages() throws IOException {
        String fileContents = JsonFileReader.readFileToString(magicItemFilePath);
        String[] pageLines = fileContents.split("\n");
        return pageLines.length;
    }

    protected void populateFilteredMagicItems(List<Item> filterItemList) {
        //TODO:: Grab all the info for magic items
        for (Object name : nameJsonArray) {
            Item item = new Item(name.toString(), "rarity", "type", "attunement", "details");
            filterItemList.add(item);
        }
    }
}
