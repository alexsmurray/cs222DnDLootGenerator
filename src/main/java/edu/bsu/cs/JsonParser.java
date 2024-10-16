package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.ArrayList;

public class JsonParser {
    protected static JSONArray parseStandardItemName(String stringifiedJson){
        return JsonPath.read(stringifiedJson, "$..name");
    }

    protected static void parseMagicItem(ArrayList<JSONArray> jsonArrayArrayList, String stringifiedJson){
        jsonArrayArrayList.add(parseMagicItemName(stringifiedJson));
    }

    protected static JSONArray parseMagicItemName(String stringifiedJson) {
        JSONArray jsonArray = buildJsonArray(stringifiedJson, "name");
        return (JSONArray) jsonArray.get(ItemBuilder.selectRandomItemIndex(jsonArray));
    }

    protected static JSONArray parseMagicItemRarity(String stringifiedJson) {
        JSONArray jsonArray = buildJsonArray(stringifiedJson, "rarity");
        return (JSONArray) jsonArray.get(ItemBuilder.selectRandomItemIndex(jsonArray));
    }

    protected static JSONArray parseMagicItemType(String stringifiedJson) {
        JSONArray jsonArray = buildJsonArray(stringifiedJson, "type");
        return (JSONArray) jsonArray.get(ItemBuilder.selectRandomItemIndex(jsonArray));
    }

    protected static JSONArray parseMagicItemAttunement(String stringifiedJson) {
        JSONArray jsonArray = buildJsonArray(stringifiedJson, "requires_attunement");
        return (JSONArray) jsonArray.get(ItemBuilder.selectRandomItemIndex(jsonArray));
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
