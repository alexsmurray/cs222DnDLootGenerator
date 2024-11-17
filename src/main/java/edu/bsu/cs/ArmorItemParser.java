package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Objects;

public class ArmorItemParser {

    private final String stringifiedJson;

    public ArmorItemParser(String stringifiedJson) {
        this.stringifiedJson = stringifiedJson;
    }
    protected JSONArray parseArmorItemName(){
        return JsonPath.read(stringifiedJson, "$..name");
    }

    protected Dictionary<Integer, String> parseAllArmorStats(int randomIndex) {
        Dictionary<Integer, String> statDictionary = new Hashtable<>();

        statDictionary.put(1, parseArmorClassDisplay().get(randomIndex).toString());
        statDictionary.put(2, parseArmorCategory().get(randomIndex).toString());
        statDictionary.put(3, parseStealthDisadvantage().get(randomIndex).toString());
        statDictionary.put(4, verifyStrengthRequirement(parseStrengthScoreRequirement().get(randomIndex)).toString());
        return statDictionary;
    }

    protected JSONArray parseArmorClassDisplay(){
        return JsonPath.read(stringifiedJson, "$..ac_display");
    }

    protected JSONArray parseArmorCategory(){
        return JsonPath.read(stringifiedJson, "$..category");
    }

    protected JSONArray parseStealthDisadvantage(){
        return JsonPath.read(stringifiedJson, "$..grants_stealth_disadvantage");
    }

    protected JSONArray parseStrengthScoreRequirement(){
        return JsonPath.read(stringifiedJson, "$..strength_score_required");
    }

    private Object verifyStrengthRequirement(Object parsedItem) {
        return Objects.requireNonNullElse(parsedItem, "None");
    }
}
