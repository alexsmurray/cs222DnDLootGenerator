package edu.bsu.cs;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.util.*;

public class ItemBuilder {

    MagicItemsParser attributeParser = new MagicItemsParser();
    StandardItemsParser standardItemParser = new StandardItemsParser();

    protected List<Item> generateAmountOfItems() throws IOException {
        List<Item> itemsList = new ArrayList<>();
        for (int i = 0; i < Configuration.getNumItemsRequested(); i++) {
            itemsList.add(generateAny());
        }
        return itemsList;
    }

    private Item generateAny() throws IOException {
        return switch (new Random().nextInt(3)) {
            case 0 -> generateWeapon("src/main/resources/weapons.txt");
            case 1 -> generateArmor("src/main/resources/armor.txt");
            case 2 -> generateMagicItem("src/main/resources/magicitems.txt");
            default -> throw new IllegalStateException("Unexpected value: " + 3);
        };
    }

    protected Item generateWeapon(String filePath) throws IOException {
        JSONArray nameJsonArray =  standardItemParser.parseStandardItemName(JsonFileReader.readFileToString(filePath));
        int randomIndex = selectRandomItemIndex(nameJsonArray);
        Item item = new Item(nameJsonArray.get(randomIndex).toString(), OutputFormatter.formatWeaponStats(getWeaponStats(JsonFileReader.readFileToString(filePath), randomIndex)));
        item.setType("Weapon");
        return item;
    }

    protected Item generateArmor(String filePath) throws IOException {
        JSONArray nameJsonArray =  standardItemParser.parseStandardItemName(JsonFileReader.readFileToString(filePath));
        int randomIndex = selectRandomItemIndex(nameJsonArray);
        Item item = new Item(nameJsonArray.get(randomIndex).toString(), OutputFormatter.formatArmorStats(getArmorStats(JsonFileReader.readFileToString(filePath), randomIndex)));
        item.setType("Armor");
        return item;
    }

    protected Item generateMagicItem(String filePath) throws IOException {
        String fileContents = JsonFileReader.readFileToString(filePath);
        String[] pageLines = fileContents.split("\n");
        int pageIndex = new Random().nextInt(pageLines.length);

        JSONArray nameJsonArray =  attributeParser.parseMagicItemName(fileContents, pageIndex);
        JSONArray rarityJsonArray =  attributeParser.parseMagicItemRarity(fileContents, pageIndex);
        JSONArray typeJsonArray =  attributeParser.parseMagicItemType(fileContents, pageIndex);
        JSONArray attunementJsonArray =  attributeParser.parseMagicItemAttunement(fileContents, pageIndex);
        JSONArray descriptionJsonArray = attributeParser.parseMagicItemDescription(fileContents,pageIndex);

        int selectedIndex = selectRandomItemIndex(nameJsonArray);

        return new Item(
                nameJsonArray.get(selectedIndex).toString(),
                OutputFormatter.formatRarity(rarityJsonArray.get(selectedIndex).toString()),
                typeJsonArray.get(selectedIndex).toString(),
                OutputFormatter.formatAttunement(attunementJsonArray.get(selectedIndex).toString()),
                descriptionJsonArray.get(selectedIndex).toString()
                );
    }

    protected static int selectRandomItemIndex(JSONArray array){
        return new Random().nextInt(array.size());
    }

    public Dictionary<Integer, String> getArmorStats(String filePath, int randomIndex) {
        Dictionary<Integer, String> statDictionary = new Hashtable<>();

        statDictionary.put(1, standardItemParser.parseArmorClassDisplay(filePath).get(randomIndex).toString());
        statDictionary.put(2, standardItemParser.parseArmorCategory(filePath).get(randomIndex).toString());
        statDictionary.put(3, standardItemParser.parseStealthDisadvantage(filePath).get(randomIndex).toString());
        statDictionary.put(4, checkForNullStat(standardItemParser.parseStrengthScoreRequirement(filePath).get(randomIndex)).toString());
        return statDictionary;
    }

    public Dictionary<Integer, String> getWeaponStats(String filePath, int randomIndex) {
        Dictionary<Integer, String> statDictionary = new Hashtable<>();

        statDictionary.put(1, standardItemParser.parseWeaponIsMartial(filePath).get(randomIndex).toString());
        statDictionary.put(2, standardItemParser.parseWeaponIsSimple(filePath).get(randomIndex).toString());
        statDictionary.put(3, standardItemParser.parseDamageDice(filePath).get(randomIndex).toString());
        statDictionary.put(4, standardItemParser.parseWeaponReach(filePath).get(randomIndex).toString());
        statDictionary.put(5, standardItemParser.parseWeaponRange(filePath).get(randomIndex).toString());
        statDictionary.put(6, standardItemParser.parseWeaponLongRange(filePath).get(randomIndex).toString());
        statDictionary.put(7, OutputFormatter.formatWeaponDamageType(standardItemParser.parseWeaponDamageType(filePath).get(randomIndex)).toString());
        statDictionary.put(8, standardItemParser.parseWeaponProperties(filePath).get(randomIndex).toString());
        statDictionary.put(9, standardItemParser.parseWeaponIsLance(filePath).get(randomIndex).toString());
        statDictionary.put(10, standardItemParser.parseWeaponIsNet(filePath).get(randomIndex).toString());
        return statDictionary;
    }

    private Object checkForNullStat(Object parsedItem) {
        return Objects.requireNonNullElse(parsedItem, "None");
    }
}
