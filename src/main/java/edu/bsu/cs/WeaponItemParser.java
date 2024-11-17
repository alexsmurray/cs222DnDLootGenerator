package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.Dictionary;
import java.util.Hashtable;

public class WeaponItemParser {


    protected JSONArray parseWeaponItemName(String stringifiedJson){
        return JsonPath.read(stringifiedJson, "$..name");
    }

    protected Dictionary<Integer, String> parseAllWeaponStats(String stringifiedJson, int randomIndex) {
        Dictionary<Integer, String> statDictionary = new Hashtable<>();

        statDictionary.put(1, parseWeaponIsMartial(stringifiedJson).get(randomIndex).toString());
        statDictionary.put(2, parseWeaponIsSimple(stringifiedJson).get(randomIndex).toString());
        statDictionary.put(3, parseDamageDice(stringifiedJson).get(randomIndex).toString());
        statDictionary.put(4, parseWeaponReach(stringifiedJson).get(randomIndex).toString());
        statDictionary.put(5, OutputFormatter.formatWeaponDamageType(parseWeaponDamageType(stringifiedJson).get(randomIndex)).toString());
        statDictionary.put(6, parseWeaponProperties(stringifiedJson).get(randomIndex).toString().replace("\\",""));
        statDictionary.put(7, parseWeaponIsLance(stringifiedJson).get(randomIndex).toString());
        statDictionary.put(8, parseWeaponIsNet(stringifiedJson).get(randomIndex).toString());

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
