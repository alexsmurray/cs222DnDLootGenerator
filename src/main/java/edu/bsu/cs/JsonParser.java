package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

public class JsonParser {
    public static JSONArray getRarityFromJsonSample(String stringifiedJson) {
        return JsonPath.read(stringifiedJson, "$..rarity");
    }

    public static JSONArray getNameFromJsonSample(String stringifiedJson) {
        return JsonPath.read(stringifiedJson, "$..name");
    }

    //Could be boolean instead up to team
    public static JSONArray getAttunementFromJsonSample(String stringifiedJson) {
        return JsonPath.read(stringifiedJson, "$..requires_attunement");
    }
}
