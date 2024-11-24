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
        String[] attributes = {"name", "rarity", "type", "requires_attunement", "desc"};

        for(String attribute : attributes) {
            magicItemDetailsList.put(attribute, parseAttribute(attribute,pageIndex));
        }
        return magicItemDetailsList;
    }

    protected JSONArray parseAttribute(String attribute, int pageIndex) {
        return (JSONArray) jsonArrayBuilder.buildJsonArrayOfMagicItemPages(stringifiedJson, attribute).get(pageIndex);
    }

}
