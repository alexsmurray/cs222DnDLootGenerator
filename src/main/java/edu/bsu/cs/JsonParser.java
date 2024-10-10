package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

public class JsonParser {
    public static JSONArray parseRarity(String stringifiedJson) {
        return JsonPath.read(stringifiedJson, "$..rarity");
    }

    public static JSONArray parseName(String stringifiedJson) {
        return JsonPath.read(stringifiedJson, "$..name");
    }

    //Could be boolean instead up to team
    public static JSONArray parseAttunement(String stringifiedJson) {
        return JsonPath.read(stringifiedJson, "$..requires_attunement");
    }
}
