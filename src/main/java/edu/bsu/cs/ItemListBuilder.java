package edu.bsu.cs;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.util.List;

public class ItemListBuilder {

    private String armorFilePath = "src/main/resources/dataFiles/armor.txt";
    private String weaponFilePath = "src/main/resources/dataFiles/weapons.txt";
    private String magicItemFilePath = "src/main/resources/dataFiles/magicitems.txt";
    private ArmorItemParser armorItemParser = new ArmorItemParser(JsonFileReader.readFileToString(armorFilePath));
    private WeaponItemParser weaponItemParser = new WeaponItemParser(JsonFileReader.readFileToString(weaponFilePath));
    private MagicItemsParser magicItemsParser = new MagicItemsParser(JsonFileReader.readFileToString(magicItemFilePath));
    private JSONArray nameJsonArray;
    private ItemFilter itemFilter = new ItemFilter();

    public ItemListBuilder() throws IOException {}

    protected void populateListOfItems(List<String> filterItemList) throws IOException {
        populateListWithArmorItems(filterItemList);
        populateListWithWeaponItems(filterItemList);
        populateListWithMagicItems(filterItemList);
    }

    protected void populateListWithArmorItems(List<String> filterItemList) throws IOException {
        nameJsonArray =  armorItemParser.parseArmorItemName();

        if (itemFilter.checkForItemTypeEnabled("armor")) {
            populateFilteredList(filterItemList);
        }
    }

    protected void populateListWithWeaponItems(List<String> filterItemList) {
        nameJsonArray =  weaponItemParser.parseWeaponItemName();
        //TODO:: Actually filter
        populateFilteredList(filterItemList);
    }

    protected void populateListWithMagicItems(List<String> filterItemList) throws IOException {
        int magicItemPages = fetchNumberOfMagicItemPages();
        for (int page = 0; page < magicItemPages; page++) {
            nameJsonArray = magicItemsParser.parseMagicItemName(page);
            //TODO:: Actually filter
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
