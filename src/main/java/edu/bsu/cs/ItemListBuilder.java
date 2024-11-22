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

    protected void populateListWithWeaponItems(List<String> filterItemList) throws IOException {
        nameJsonArray =  weaponItemParser.parseWeaponItemName();

        if (itemFilter.checkForItemTypeEnabled("weapon")) {
            populateFilteredList(filterItemList);
        }
    }

    protected void populateListWithMagicItems(List<String> filterItemList) throws IOException {
        int magicItemPages = fetchNumberOfMagicItemPages();
        for (int page = 0; page < magicItemPages; page++) {
            nameJsonArray = magicItemsParser.parseMagicItemName(page);
            if (itemFilter.checkForItemTypeEnabled("magicEquipment")) {
                populateFilteredList(filterItemList);
            }
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
