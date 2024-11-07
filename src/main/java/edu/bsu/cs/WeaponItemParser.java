package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.Dictionary;
import java.util.Hashtable;

public class WeaponItemParser {

    protected JSONArray parseStandardItemName(String stringifiedJson){
        return JsonPath.read(stringifiedJson, "$..name");
    }

    protected Dictionary<Integer, String> parseAllWeaponStats(String filePath, int randomIndex) {
        Dictionary<Integer, String> statDictionary = new Hashtable<>();

        statDictionary.put(1, parseWeaponIsMartial(filePath).get(randomIndex).toString());
        statDictionary.put(2, parseWeaponIsSimple(filePath).get(randomIndex).toString());
        statDictionary.put(3, parseDamageDice(filePath).get(randomIndex).toString());
        statDictionary.put(4, parseWeaponReach(filePath).get(randomIndex).toString());
        statDictionary.put(5, parseWeaponRange(filePath).get(randomIndex).toString());
        statDictionary.put(6, parseWeaponLongRange(filePath).get(randomIndex).toString());
        statDictionary.put(7, OutputFormatter.formatWeaponDamageType(parseWeaponDamageType(filePath).get(randomIndex)).toString());
        statDictionary.put(8, parseWeaponProperties(filePath).get(randomIndex).toString().replace("\\",""));
        statDictionary.put(9, parseWeaponIsLance(filePath).get(randomIndex).toString());
        statDictionary.put(10, parseWeaponIsNet(filePath).get(randomIndex).toString());

        return statDictionary;
    }

    protected JSONArray parseWeaponIsMartial(String stringifiedJson){
        return JsonPath.read(stringifiedJson, "$..is_martial");
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

    protected JSONArray parseWeaponIsLance(String stringifiedJson){
        return JsonPath.read(stringifiedJson, "$..is_lance");
    }

    protected JSONArray parseWeaponIsNet(String stringifiedJson){
        return JsonPath.read(stringifiedJson, "$..is_net");
    }

    protected JSONArray parseWeaponIsSimple(String stringifiedJson){
        return JsonPath.read(stringifiedJson, "$..is_simple");
    }

    protected JSONArray parseWeaponDamageType(String stringifiedJson){
        return JsonPath.read(stringifiedJson, "$..damage_type");
    }

}
