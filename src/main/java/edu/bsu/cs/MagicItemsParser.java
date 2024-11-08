package edu.bsu.cs;

import net.minidev.json.JSONArray;

import java.util.Hashtable;

public class MagicItemsParser {

    JsonArrayBuilder jsonArrayBuilder = new JsonArrayBuilder();

    protected Hashtable<String, JSONArray> parseAllMagicItemDetails(String fileContents, int pageIndex) {
        Hashtable<String, JSONArray> magicItemDetailsList = new Hashtable<>();

        magicItemDetailsList.put("name", parseMagicItemName(fileContents, pageIndex));
        magicItemDetailsList.put("rarity", parseMagicItemRarity(fileContents, pageIndex));
        magicItemDetailsList.put("type", parseMagicItemType(fileContents, pageIndex));
        magicItemDetailsList.put("attunement", parseMagicItemAttunement(fileContents, pageIndex));
        magicItemDetailsList.put("description", parseMagicItemDescription(fileContents,pageIndex));

        return magicItemDetailsList;
    }

    protected JSONArray parseMagicItemName(String stringifiedJson, int pageIndex) {
        JSONArray jsonArray = jsonArrayBuilder.buildJsonArrayOfMagicItemPages(stringifiedJson, "name");
        return (JSONArray) jsonArray.get(pageIndex);
    }

    protected JSONArray parseMagicItemRarity(String stringifiedJson, int pageIndex) {
        JSONArray jsonArray = jsonArrayBuilder.buildJsonArrayOfMagicItemPages(stringifiedJson, "rarity");
        return (JSONArray) jsonArray.get(pageIndex);
    }

    protected JSONArray parseMagicItemType(String stringifiedJson, int pageIndex) {
        JSONArray jsonArray = jsonArrayBuilder.buildJsonArrayOfMagicItemPages(stringifiedJson, "type");
        return (JSONArray) jsonArray.get(pageIndex);
    }

    protected JSONArray parseMagicItemAttunement(String stringifiedJson, int pageIndex) {
        JSONArray jsonArray = jsonArrayBuilder.buildJsonArrayOfMagicItemPages(stringifiedJson, "requires_attunement");
        return (JSONArray) jsonArray.get(pageIndex);
    }

    protected JSONArray parseMagicItemDescription(String stringifiedJson, int pageIndex) {
        JSONArray jsonArray = jsonArrayBuilder.buildJsonArrayOfMagicItemPages(stringifiedJson, "desc");
        return (JSONArray) jsonArray.get(pageIndex);
    }
}
