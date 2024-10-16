package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

public class JsonParser {

    protected static JSONArray parseName(String stringifiedJson) {
        JSONArray jsonArray = buildJsonArrayFromJsonString(stringifiedJson);
        boolean isMagicItem = jsonArray.size() > 1;

        if (isMagicItem) {
            return (JSONArray) jsonArray.get(ItemBuilder.selectRandomItemIndex(jsonArray));
        } else
            return (JSONArray) jsonArray.getFirst();
    }

    private static JSONArray buildJsonArrayFromJsonString(String stringifiedJson){
        JSONArray jsonArray = new JSONArray();
        String[] magicItemJsonArray = stringifiedJson.split("\n");

        for (String magicItemPage : magicItemJsonArray) {
            jsonArray.add(JsonPath.read(magicItemPage, "$..name"));
        }
        return jsonArray;
    }

    protected static JSONArray parseRarity(String stringifiedJson) {
        return JsonPath.read(stringifiedJson, "$..rarity");
    }

    protected static JSONArray parseType(String stringifiedJson) {
        return JsonPath.read(stringifiedJson, "$..type");
    }

    //Could be boolean instead up to team
    protected static JSONArray parseAttunement(String stringifiedJson) {
        return JsonPath.read(stringifiedJson, "$..requires_attunement");
    }
    protected static String parseNext(String stringifiedJson) {
        return JsonPath.read(stringifiedJson, "$..next").toString();
    }
}
