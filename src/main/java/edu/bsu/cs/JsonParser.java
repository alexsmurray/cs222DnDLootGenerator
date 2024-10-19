package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

public class JsonParser {

    static JsonArrayBuilder jsonArrayBuilder = new JsonArrayBuilder();

    protected static JSONArray parseStandardItemName(String stringifiedJson){
        return JsonPath.read(stringifiedJson, "$..name");
    }

    protected static JSONArray parseMagicItemName(String stringifiedJson, int pageIndex) {
        JSONArray jsonArray = jsonArrayBuilder.buildJsonArrayOfMagicItemPages(stringifiedJson, "name");
        return (JSONArray) jsonArray.get(pageIndex);
    }

    protected static JSONArray parseMagicItemRarity(String stringifiedJson, int pageIndex) {
        JSONArray jsonArray = jsonArrayBuilder.buildJsonArrayOfMagicItemPages(stringifiedJson, "rarity");
        return (JSONArray) jsonArray.get(pageIndex);
    }

    protected static JSONArray parseMagicItemType(String stringifiedJson, int pageIndex) {
        JSONArray jsonArray = jsonArrayBuilder.buildJsonArrayOfMagicItemPages(stringifiedJson, "type");
        return (JSONArray) jsonArray.get(pageIndex);
    }

    protected static JSONArray parseMagicItemAttunement(String stringifiedJson, int pageIndex) {
        JSONArray jsonArray = jsonArrayBuilder.buildJsonArrayOfMagicItemPages(stringifiedJson, "requires_attunement");
        return (JSONArray) jsonArray.get(pageIndex);
    }

    protected static String parseNext(String stringifiedJson) {
        return JsonPath.read(stringifiedJson, "$..next").toString();
    }

}
