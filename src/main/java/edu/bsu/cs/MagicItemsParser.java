package edu.bsu.cs;

import net.minidev.json.JSONArray;

import java.util.Hashtable;

public class MagicItemsParser {

    private final String stringifiedJson;

    public MagicItemsParser(String stringifiedJson) {
        this.stringifiedJson = stringifiedJson;
    }

    JsonArrayBuilder jsonArrayBuilder = new JsonArrayBuilder();

    protected Hashtable<String, JSONArray> parseAllMagicItemDetails(int pageIndex) {
        Hashtable<String, JSONArray> magicItemDetailsList = new Hashtable<>();

        magicItemDetailsList.put("name", parseMagicItemName(pageIndex));
        magicItemDetailsList.put("rarity", parseMagicItemRarity(pageIndex));
        magicItemDetailsList.put("type", parseMagicItemType(pageIndex));
        magicItemDetailsList.put("attunement", parseMagicItemAttunement(pageIndex));
        magicItemDetailsList.put("description", parseMagicItemDescription(pageIndex));

        return magicItemDetailsList;
    }

    protected JSONArray parseMagicItemName(int pageIndex) {
        JSONArray jsonArray = jsonArrayBuilder.buildJsonArrayOfMagicItemPages(stringifiedJson, "name");
        return (JSONArray) jsonArray.get(pageIndex);
    }

    protected JSONArray parseMagicItemRarity(int pageIndex) {
        JSONArray jsonArray = jsonArrayBuilder.buildJsonArrayOfMagicItemPages(stringifiedJson, "rarity");
        return (JSONArray) jsonArray.get(pageIndex);
    }

    protected JSONArray parseMagicItemType(int pageIndex) {
        JSONArray jsonArray = jsonArrayBuilder.buildJsonArrayOfMagicItemPages(stringifiedJson, "type");
        return (JSONArray) jsonArray.get(pageIndex);
    }

    protected JSONArray parseMagicItemAttunement(int pageIndex) {
        JSONArray jsonArray = jsonArrayBuilder.buildJsonArrayOfMagicItemPages(stringifiedJson, "requires_attunement");
        return (JSONArray) jsonArray.get(pageIndex);
    }

    protected JSONArray parseMagicItemDescription(int pageIndex) {
        JSONArray jsonArray = jsonArrayBuilder.buildJsonArrayOfMagicItemPages(stringifiedJson, "desc");
        return (JSONArray) jsonArray.get(pageIndex);
    }
}
