package edu.bsu.cs;

import net.minidev.json.JSONArray;

public class MagicItemsParser {

    JsonArrayBuilder jsonArrayBuilder = new JsonArrayBuilder();

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