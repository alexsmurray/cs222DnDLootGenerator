package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

public class JsonParser {
    protected static JSONArray parseStandardItemName(String stringifiedJson){
        return JsonPath.read(stringifiedJson, "$..name");
    }

    protected static JSONArray parseMagicItemName(String stringifiedJson, int pageIndex) {
        JSONArray jsonArray = buildJsonArray(stringifiedJson, "name");
        return (JSONArray) jsonArray.get(pageIndex);
    }

    protected static JSONArray parseMagicItemRarity(String stringifiedJson, int pageIndex) {
        JSONArray jsonArray = buildJsonArray(stringifiedJson, "rarity");
        return (JSONArray) jsonArray.get(pageIndex);
    }

    protected static JSONArray parseMagicItemType(String stringifiedJson, int pageIndex) {
        JSONArray jsonArray = buildJsonArray(stringifiedJson, "type");
        return (JSONArray) jsonArray.get(pageIndex);
    }

    protected static JSONArray parseMagicItemAttunement(String stringifiedJson, int pageIndex) {
        JSONArray jsonArray = buildJsonArray(stringifiedJson, "requires_attunement");
        return (JSONArray) jsonArray.get(pageIndex);
    }

    protected static String parseNext(String stringifiedJson) {
        return JsonPath.read(stringifiedJson, "$..next").toString();
    }

    private static JSONArray buildJsonArray(String stringifiedJson, String category){
        JSONArray jsonArray = new JSONArray();
        String[] magicItemJsonArray = stringifiedJson.split("\n");
        for (String magicItemPage : magicItemJsonArray) {
            jsonArray.add(JsonPath.read(magicItemPage, "$.." + category));
        }
        return jsonArray;
    }

}
