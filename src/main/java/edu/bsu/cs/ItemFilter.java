package edu.bsu.cs;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.util.List;

public class ItemFilter {

    String armorFilePath = "src/main/resources/dataFiles/armor.txt";
    String weaponFilePath = "src/main/resources/dataFiles/weapons.txt";
    String magicItemFilePath = "src/main/resources/dataFiles/magicitems.txt";
    ArmorItemParser armorItemParser = new ArmorItemParser(JsonFileReader.readFileToString(armorFilePath));
    WeaponItemParser weaponItemParser = new WeaponItemParser(JsonFileReader.readFileToString(weaponFilePath));
    MagicItemsParser magicItemsParser = new MagicItemsParser(JsonFileReader.readFileToString(magicItemFilePath));
    JSONArray nameJsonArray;

    public ItemFilter() throws IOException {}

    protected void populateListOfItems(List<String> filterItemList) throws IOException {
        populateListWithArmorItems(filterItemList);
        populateListWithWeaponItems(filterItemList);
        populateListWithMagicItems(filterItemList);
    }

    protected void populateListWithArmorItems(List<String> filterItemList) {
        nameJsonArray =  armorItemParser.parseArmorItemName();
        populateFilteredList(filterItemList);
    }

    protected void populateListWithWeaponItems(List<String> filterItemList) {

        nameJsonArray =  weaponItemParser.parseWeaponItemName();
        populateFilteredList(filterItemList);
    }

    protected void populateListWithMagicItems(List<String> filterItemList) throws IOException {
        int magicItemPages = fetchNumberOfMagicItemPages();
        for (int page = 0; page < magicItemPages; page++) {
            nameJsonArray = magicItemsParser.parseMagicItemName(page);
            populateFilteredList(filterItemList);
        }
    }

    protected int fetchNumberOfMagicItemPages() throws IOException {
        String fileContents = JsonFileReader.readFileToString(magicItemFilePath);
        String[] pageLines = fileContents.split("\n");
        return pageLines.length;
    }

    protected void populateFilteredList(List<String> filterItemList) {
        for (Object item : nameJsonArray) {
            filterItemList.add(item.toString());
        }
    }
}
