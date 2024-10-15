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

    protected static JSONArray parseType(String stringifiedJson) {
        return JsonPath.read(stringifiedJson, "$..type");
    }

    //Could be boolean instead up to team
    public static JSONArray parseAttunement(String stringifiedJson) {
        return JsonPath.read(stringifiedJson, "$..requires_attunement");
    }
    public static String parseNext(String stringifiedJson) {
        return JsonPath.read(stringifiedJson, "$..next").toString();
    }
}
