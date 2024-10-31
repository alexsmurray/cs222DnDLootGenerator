package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

public class StandardItemsParser {

    protected JSONArray parseStandardItemName(String stringifiedJson){
        return JsonPath.read(stringifiedJson, "$..name");
    }

    //Rename for weapon use
    protected JSONArray parseStandardItemResults(String stringifiedJson) {
        return JsonPath.read(stringifiedJson, "$.results");
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

    protected JSONArray parseWeaponProperties(String stringifiedJson){
        return JsonPath.read(stringifiedJson, "$..properties");
    }

    protected JSONArray parseDamageDice(String stringifiedJson){
        return JsonPath.read(stringifiedJson, "$..damage_dice");
    }

    protected JSONArray parseWeaponReach(String stringifiedJson){
        return JsonPath.read(stringifiedJson, "$..reach");
    }

    protected JSONArray parseWeaponRange(String stringifiedJson){
        return JsonPath.read(stringifiedJson, "$..range");
    }

    protected JSONArray parseWeaponLongRange(String stringifiedJson){
        return JsonPath.read(stringifiedJson, "$..long_range");
    }

}
