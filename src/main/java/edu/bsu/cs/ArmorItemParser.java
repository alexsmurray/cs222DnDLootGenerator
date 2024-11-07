package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Objects;

public class ArmorItemParser {

    protected Dictionary<Integer, String> parseAllArmorStats(String filePath, int randomIndex) {
        Dictionary<Integer, String> statDictionary = new Hashtable<>();

        statDictionary.put(1, parseArmorClassDisplay(filePath).get(randomIndex).toString());
        statDictionary.put(2, parseArmorCategory(filePath).get(randomIndex).toString());
        statDictionary.put(3, parseStealthDisadvantage(filePath).get(randomIndex).toString());
        statDictionary.put(4, verifyStrengthRequirement(parseStrengthScoreRequirement(filePath).get(randomIndex)).toString());
        return statDictionary;
    }

    protected JSONArray parseArmorClassDisplay(String stringifiedJson){
        return JsonPath.read(stringifiedJson, "$..ac_display");
    }

    protected JSONArray parseArmorCategory(String stringifiedJson){
        return JsonPath.read(stringifiedJson, "$..category");
    }

    protected JSONArray parseStealthDisadvantage(String stringifiedJson){
        return JsonPath.read(stringifiedJson, "$..grants_stealth_disadvantage");
    }

    protected JSONArray parseStrengthScoreRequirement(String stringifiedJson){
        return JsonPath.read(stringifiedJson, "$..strength_score_required");
    }

    private Object verifyStrengthRequirement(Object parsedItem) {
        return Objects.requireNonNullElse(parsedItem, "None");
    }
}
